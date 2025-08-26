package kb24.ehsan.sample.screen.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kb24.ehsan.sample.domain.PoetRepository
import kb24.ehsan.sample.domain.ResultState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FirstViewModel(
    private val repository: PoetRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<FirstViewState>(FirstViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _singleAction = MutableSharedFlow<FirstViewSingleAction>()
    val singleAction = _singleAction.asSharedFlow()

    init {
        viewModelScope.launch {
            val result = repository.getPoets()
            when (result) {
                is ResultState.Failed -> _viewState.update {
                    FirstViewState.Error(
                        result.message
                    )
                }

                is ResultState.Success -> _viewState.update {
                    FirstViewState.Success(
                        items = buildList {
                            add(result.data)
                        }
                    )
                }
            }
        }
    }

    fun onEvent(event: FirstViewEvent) {
        when (event) {
            is FirstViewEvent.OnItemClick -> {
                viewModelScope.launch {
                    _singleAction.emit(FirstViewSingleAction.NavigateNext)
                }
            }
        }
    }

}