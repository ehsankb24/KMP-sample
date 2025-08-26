package kb24.ehsan.sample.domain

interface PoetRepository {
    suspend fun getPoets(): ResultState<String>
}