package kb24.ehsan.sample

import io.ktor.client.HttpClient
import kb24.ehsan.sample.data.PoetRepositoryImpl
import kb24.ehsan.sample.domain.PoetRepository
import kb24.ehsan.sample.screen.first.FirstViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { getPlatform() }
    /*single { Greeting(get()) }*/
    single {
        HttpClient {
            engine {
                // this: HttpClientEngineConfig
                pipelining = true
            }
        }
    }
    single<PoetRepository> { PoetRepositoryImpl(get()) }
    viewModelOf(::FirstViewModel)
} 