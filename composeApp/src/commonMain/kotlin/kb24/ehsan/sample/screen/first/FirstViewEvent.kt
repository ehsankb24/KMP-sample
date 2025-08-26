package kb24.ehsan.sample.screen.first

sealed interface FirstViewEvent {
    data class OnItemClick(val index: Int) : FirstViewEvent
}