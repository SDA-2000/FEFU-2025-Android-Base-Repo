package co.feip.fefu2025.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.usecases.SearchAnimeByNameUseCase
import co.feip.fefu2025.presentation.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchAnimeByNameUseCase: SearchAnimeByNameUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    var searchState by mutableStateOf<SearchUiState>(SearchUiState.Idle)
        private set

    init {
        observeSearchQuery()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(400)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isBlank()) {
                        searchState = SearchUiState.Empty
                        return@collectLatest
                    }

                    searchState = SearchUiState.Loading
                    try {
                        val result = searchAnimeByNameUseCase.exec(query)
                        searchState = if (result.isEmpty()) {
                            SearchUiState.Empty
                        } else {
                            SearchUiState.Success(result)
                        }
                    } catch (e: Exception) {
                        searchState = SearchUiState.Error(e.message ?: "Ошибка поиска")
                    }
                }
        }
    }
    fun retry() {
        onSearchQueryChanged(_searchQuery.value)
    }
}

