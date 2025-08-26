package kb24.ehsan.sample.screen.first

sealed interface FirstViewSingleAction {
    data object NavigateNext : FirstViewSingleAction
}