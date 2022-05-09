package br.com.spectacle.app.feature.musics.presentation

import androidx.lifecycle.viewModelScope
import br.com.spectacle.app.core.ds.arch.ViewModel
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetOption
import br.com.spectacle.app.core.ds.component.bottomsheet.BottomSheetState
import br.com.spectacle.app.feature.musics.domain.model.Music
import br.com.spectacle.app.feature.musics.domain.usecase.AddFavoriteMusicUseCase
import br.com.spectacle.app.feature.musics.domain.usecase.GetFavoritesMusicsUseCase
import br.com.spectacle.app.feature.musics.domain.usecase.GetMusicsByQueryUseCase
import br.com.spectacle.app.feature.musics.domain.usecase.RemoveFavoriteMusicUseCase
import br.com.spectacle.app.feature.musics.presentation.components.MusicsSwitchOption
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val BOTTOM_SHEET_DELAY = 200L
private const val REQUEST_DELAY = 2 * 1000L

internal class MusicViewModel(
    private val getFavoritesMusicsUseCase: GetFavoritesMusicsUseCase,
    private val getMusicsByQueryUseCase: GetMusicsByQueryUseCase,
    private val addFavoriteMusicUseCase: AddFavoriteMusicUseCase,
    private val removeFavoriteMusicUseCase: RemoveFavoriteMusicUseCase
) : ViewModel<MusicsUiState, MusicsUiAction>(MusicsUiState()) {
    private var jobGetMusicByQuery: Job? = null

    private val query: MutableStateFlow<String?> = MutableStateFlow(null)
    init {
        collectQuery()
        getFavoriteMusics()
    }

    private fun collectQuery(){
        viewModelScope.launch {
            query.debounce(500).filterNotNull().collect { query ->
                getMusicByQuery(query)
            }
        }
    }

    fun updateQuery(query: String){
        this.query.value = query
    }

    private fun getMusicByQuery(query: String){
        jobGetMusicByQuery?.cancel()
        jobGetMusicByQuery = viewModelScope.launch {
            try{
                if(query == state.value.query){
                    handleQueryMusics(state.value.queryMusics.orEmpty())
                    return@launch
                }

                setLoading(true)
                val musics = withContext(IO){
                    getMusicsByQueryUseCase(query)
                }
                handleQueryMusics(musics)
            }catch (t: Throwable){
                handleError(t)
            }finally {
                setLoading(false)
            }
        }
    }

    private fun handleQueryMusics(musics: List<Music>){
        setState { state ->
            state.copy(
                queryMusics = musics,
                query = query.value
            )
        }
    }

    private fun getFavoriteMusics(){
        viewModelScope.launch {
            try{
                setLoading(true)
                val musics = withContext(IO){
                    delay(REQUEST_DELAY)
                    getFavoritesMusicsUseCase()
                }
                handleFavoriteMusics(musics)
            }catch (t: Throwable){
                handleError(t)
            }finally {
                setLoading(false)
            }
        }
    }

    private fun handleFavoriteMusics(musics: List<Music>){
        setState { state ->
            state.copy(favoritesMusics = musics)
        }
    }

    fun clickedTab(option: MusicsSwitchOption){
        updateTab(isFavoritesTab = option is MusicsSwitchOption.Favorites)
    }

    private fun updateTab(isFavoritesTab: Boolean) {
        setState { state ->
            state.copy(isFavoritesTab = isFavoritesTab)
        }
    }

    fun clickedMusic(music: Music) {
        viewModelScope.launch {
            setState { state ->
                val bottomSheetState = BottomSheetState.ItemAction(
                    options = getMusicActionByState(
                        state = state,
                        music = music
                    ),
                    itemId = music.id
                )
                state.copy(
                    bottomSheetState = bottomSheetState,
                    selectedMusic = music
                )
            }
            delay(BOTTOM_SHEET_DELAY)
            sendAction { MusicsUiAction.ExpandBottomSheet }
        }
    }

    fun clickedConfirmMusicAction(musicId: Long){
        handleMusicAction(musicId)
    }

    fun clickedCancelAction(){
        viewModelScope.launch {
            sendAction { MusicsUiAction.CollapseBottomSheet }
            delay(BOTTOM_SHEET_DELAY)
            setBottomSheetState(null)
        }
    }

    private fun handleMusicAction(musicId: Long) {
        viewModelScope.launch {
            try {
                val isFavoritesTab = state.value.isFavoritesTab
                setBottomSheetState(
                    BottomSheetState.Progress.Loading(
                        getMusicActionMessageLoading(isFavoritesTab)
                    )
                )
                withContext(IO){
                    delay(REQUEST_DELAY)

                    if (isFavoritesTab) {
                        removeFavoriteMusicUseCase(musicId)
                    } else {
                        addFavoriteMusicUseCase(musicId)
                    }
                }

                handleResultMusicAction(isFavoritesTab)
            } catch (t: Throwable) {
                handleError(t)
            }
        }
    }

    private fun handleResultMusicAction(isFavoritesTab: Boolean){
        setBottomSheetState(
            BottomSheetState.Progress.Success(
                getMusicActionMessageSuccess(isFavoritesTab)
            )
        )
        val favoritesMusics = state.value.favoritesMusics.toMutableList()
        val music = state.value.selectedMusic ?: return
        if(isFavoritesTab)
            favoritesMusics.removeAt(
                favoritesMusics.indexOfFirst { item -> item.id == music.id }
            )
        else
            favoritesMusics.add(0, music)

        handleFavoriteMusics(favoritesMusics)
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun setLoading(value: Boolean) {
        setState { state -> state.copy(loading = value) }
    }

    private fun setBottomSheetState(value: BottomSheetState?) {
        setState { state -> state.copy(bottomSheetState = value) }
    }

    private fun getMusicActionByState(
        state: MusicsUiState,
        music: Music
    ): List<BottomSheetOption> {
        val musicLabel = "\"${music.title} - ${music.artist.name}\""
        val message = if (state.isFavoritesTab) {
            "Remover $musicLabel das Favoritas"
        } else {
            "Adicionar $musicLabel à Favoritas"
        }
        return listOf(BottomSheetOption(message))
    }

    private fun getMusicActionMessageLoading(isFavoritesTab: Boolean): String {
        return if(isFavoritesTab) {
            "Removendo música das favoritos..."
        } else {
            "Adicionando música às favoritas..."
        }
    }

    private fun getMusicActionMessageSuccess(isFavoritesTab: Boolean): String {
        return if(isFavoritesTab) {
            "Música removida com sucesso!"
        } else {
            "Música adicionada com sucesso!"
        }
    }
}