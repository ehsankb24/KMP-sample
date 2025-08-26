package kb24.ehsan.sample.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kb24.ehsan.sample.domain.PoetRepository
import kb24.ehsan.sample.domain.ResultState

class PoetRepositoryImpl(
    private val client: HttpClient
) : PoetRepository {
    override suspend fun getPoets(): ResultState<String> {
        return try {
            val t = client.get("https://api.ganjoor.net/api/ganjoor/poets")
            if (t.status == HttpStatusCode.OK) {
                ResultState.Success(t.bodyAsText())
            } else {
                ResultState.Failed(t.status.description)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResultState.Failed(e.message.orEmpty())
        }
    }
}