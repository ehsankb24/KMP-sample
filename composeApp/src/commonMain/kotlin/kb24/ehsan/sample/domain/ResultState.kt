package kb24.ehsan.sample.domain

sealed interface ResultState<T> {
    data class Failed<T>(val message: String) : ResultState<T>
    data class Success<T>(val data: T) : ResultState<T>
}