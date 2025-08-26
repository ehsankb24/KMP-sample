package kb24.ehsan.sample.screen.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sample.composeapp.generated.resources.Res
import sample.composeapp.generated.resources.compose_multiplatform

@Composable
fun FirstScreen(
    viewModel: FirstViewModel = koinViewModel(),
    navigateNext: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                viewModel.singleAction.collect {
                    when (it) {
                        FirstViewSingleAction.NavigateNext -> navigateNext()
                    }
                }
            }
        }
    }
    FirstLayout(viewState, viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstLayout(
    viewState: FirstViewState,
    event: (event: FirstViewEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "first")
                }
            )
        }
    ) { paddingValues ->
        val modifier = Modifier.fillMaxSize().padding(paddingValues)
        when (viewState) {
            is FirstViewState.Error -> Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null
                )
                Text(text = viewState.message)
            }

            FirstViewState.Loading -> Box(modifier = modifier) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is FirstViewState.Success -> LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewState.items) {
                    Text(
                        modifier = Modifier.fillMaxWidth().clickable(
                            onClick = {
                                event(FirstViewEvent.OnItemClick(0))
                            }
                        ),
                        text = it
                    )
                }
            }
        }
    }
}
