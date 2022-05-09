package br.com.spectacle.app.core.ds.component.bottomsheet

sealed class BottomSheetState {
    data class ItemAction(
        val options: List<BottomSheetOption>,
        val itemId: Long
    ) : BottomSheetState()

    sealed class Progress(open val message: String) : BottomSheetState() {
        data class Loading(
            override val message: String
        ) : Progress(message)

        data class Success(
            override val message: String
        ) : Progress(message)

        data class Error(
            override val message: String
        ) : Progress(message)

        fun toBottomSheetProgressState(): BottomSheetProgressState {
            return when (this) {
                is Loading -> BottomSheetProgressState.LOADING
                is Success -> BottomSheetProgressState.SUCCESS
                else -> BottomSheetProgressState.ERROR
            }
        }
    }
}
