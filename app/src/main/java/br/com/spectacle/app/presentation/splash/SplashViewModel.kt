package br.com.spectacle.app.presentation.splash

import androidx.lifecycle.viewModelScope
import br.com.spectacle.app.core.ds.arch.ViewModel
import br.com.spectacle.app.domain.usecase.VerifyHasUserLoggedUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val verifyHasUserLoggedUseCase: VerifyHasUserLoggedUseCase
) : ViewModel<SplashUiState, SplashUiAction>(SplashUiState()) {
    fun splashScreenOnResume() {
        verifyHasUserLogged()
    }

    private fun verifyHasUserLogged(){
        viewModelScope.launch {
            val hasUserLogged = try{
                withContext(IO){ verifyHasUserLoggedUseCase() }
            }catch (t: Throwable){
                t.printStackTrace()
                false
            }
            handleHasUserLogged(hasUserLogged)
        }
    }

    private fun handleHasUserLogged(hasUserLogged: Boolean){
        if(hasUserLogged){
            setBackgroundState(BackgroundState.EXPANDED)
        }else{
            sendAction { SplashUiAction.NavigateToLogin }
        }
    }

    fun backgroundAnimationFinished() {
        when(state.value.backgroundState){
            BackgroundState.EXPANDED -> showButtons()
            BackgroundState.FULL_MOVIES -> navigateToMovies()
            BackgroundState.FULL_MUSICS -> navigateToMusics()
        }
    }

    private fun showButtons(){
        setState { state ->
            state.copy(isButtonsVisible = true)
        }
    }

    private fun navigateToMovies(){
        sendAction { SplashUiAction.NavigateToMovies }
    }

    private fun navigateToMusics(){
        sendAction { SplashUiAction.NavigateToMusics }
    }

    fun clickedMoviesButton() {
        handleButtonClick(BackgroundState.FULL_MOVIES)
    }

    fun clickedMusicsButton() {
        handleButtonClick(BackgroundState.FULL_MUSICS)
    }

    private fun handleButtonClick(backgroundState: BackgroundState){
        setState { state ->
            state.copy(
                isButtonsVisible = false,
            )
        }
        setBackgroundState(backgroundState)
    }

    private fun setBackgroundState(backgroundState: BackgroundState){
        setState { state ->
            state.copy(
                backgroundState = backgroundState,
            )
        }
    }
}