package br.com.spectacle.app.presentation.splash

import androidx.lifecycle.viewModelScope
import br.com.spectacle.app.core.ds.arch.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel<SplashUiState, SplashUiAction>(SplashUiState()) {
    fun splashScreenOnResume() {
        viewModelScope.launch {
            delay(500)
            setBackgroundState(BackgroundState.EXPANDED)
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
        setBackgroundState(BackgroundState.COLLAPSED)
    }

    private fun navigateToMusics(){
        sendAction { SplashUiAction.NavigateToMusics }
        setBackgroundState(BackgroundState.COLLAPSED)
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