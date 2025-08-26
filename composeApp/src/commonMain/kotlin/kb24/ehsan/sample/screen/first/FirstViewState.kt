package kb24.ehsan.sample.screen.first

sealed interface FirstViewState {
    data object Loading : FirstViewState
    data class Error(val message: String) : FirstViewState
    data class Success(val items: List<String>) : FirstViewState
}