package co.feip.fefu2025.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
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

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchAnimeByNameUseCase: SearchAnimeByNameUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    var searchState by mutableStateOf<SearchUiState>(SearchUiState.Idle)
        private set

    private var currentPage = 1
    private var isLoadingMore = false
    private var hasMore = true
    private var lastQuery = ""
    private val loadedResults = mutableListOf<Anime>()

    fun getIsLoadingMore(): Boolean = isLoadingMore
    fun getHasMore(): Boolean = hasMore

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

                    search(query, reset = true)
                }
        }
    }

    private suspend fun search(query: String, reset: Boolean = true) {
        if (query.isBlank()) return

        if (reset) {
            currentPage = 1
            loadedResults.clear()
            hasMore = true
        }

        try {
            val result = searchAnimeByNameUseCase.exec(query, currentPage)

            if (result.isNotEmpty()) {
                currentPage++

                val newResults = result.filterNot { newItem ->
                    loadedResults.any { it.id == newItem.id }
                }

                loadedResults.addAll(newResults)
                hasMore = newResults.isNotEmpty()

                searchState = SearchUiState.Success(loadedResults.toList())
            } else {
                hasMore = false
                if (loadedResults.isEmpty()) {
                    searchState = SearchUiState.Empty
                }
            }

            lastQuery = query
        } catch (e: Exception) {
            searchState = SearchUiState.Error(e.message ?: "Ошибка поиска")
        }
    }


    fun loadNextPage() {
        if (isLoadingMore || !hasMore) return

        isLoadingMore = true
        viewModelScope.launch {
            try {
                search(lastQuery, reset = false)
            } finally {
                isLoadingMore = false
            }
        }
    }

    fun retry() {
        viewModelScope.launch {
            search(lastQuery, reset = true)
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        searchState = SearchUiState.Idle
        currentPage = 1
        hasMore = true
        isLoadingMore = false
        lastQuery = ""
        loadedResults.clear()
    }
}

