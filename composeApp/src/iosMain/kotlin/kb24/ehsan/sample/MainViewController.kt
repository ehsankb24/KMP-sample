package kb24.ehsan.sample

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin

fun MainViewController(): ComposeUIViewController {
    startKoin {
        modules(appModule)
    }
    return ComposeUIViewController { App() }
}