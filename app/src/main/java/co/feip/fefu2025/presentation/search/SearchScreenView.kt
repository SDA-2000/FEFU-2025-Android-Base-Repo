package co.feip.fefu2025.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.feip.fefu2025.presentation.main.AnimeCardView
import co.feip.fefu2025.presentation.state.SearchUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenView(
    navController: NavController,
    searchScreenViewModel: SearchScreenViewModel
) {
    val state = searchScreenViewModel.searchState
    val query by searchScreenViewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(
            title = { Text("Поиск") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                }
            }
        )

        TextField(
            value = query,
            onValueChange = { searchScreenViewModel.onSearchQueryChanged(it) },
            placeholder = { Text("Введите название аниме") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is SearchUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is SearchUiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Ошибка: ${state.message}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { searchScreenViewModel.retry() }) {
                        Text("Повторить")
                    }
                }
            }

            is SearchUiState.Empty -> {
                Text("Ничего не найдено", modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is SearchUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp)) {
                    items(state.results) { anime ->
                        AnimeCardView(
                            id = anime.id,
                            title = anime.name,
                            genres = anime.genres,
                            viewers = anime.score.toString(),
                            imageUrl = anime.imageUrl,
                            modifier = Modifier,
                            onClick = {
                                navController.navigate("anime/${anime.id}")
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            is SearchUiState.Idle -> {
            }
        }
    }
}