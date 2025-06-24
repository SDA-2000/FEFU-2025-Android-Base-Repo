package co.feip.fefu2025.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel
import co.feip.fefu2025.presentation.state.UiState
import androidx.compose.runtime.getValue

@Composable
fun MainScreenView(
    viewModel: MainScreenViewModel,
    navController: NavController,
    recommendationsScreenViewModel: RecomendationsScreenViewModel
) {
    val state by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Поиск аниме...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { navController.navigate("search?clearSearch=true") },
            enabled = false
        )

        when (state) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {
                val message = (state as UiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Ошибка: $message")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.retry() }) {
                            Text("Повторить")
                        }
                    }
                }
            }

            is UiState.Success<List<Anime>> -> {
                val list = (state as UiState.Success<List<Anime>>).data

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    itemsIndexed(list) { index, anime ->
                        AnimeCardView(
                            id = anime.id,
                            title = anime.name,
                            genres = anime.genres,
                            imageUrl = anime.imageUrl,
                            viewers = anime.score.toString(),
                            onClick = {
                                navController.navigate("anime/${anime.id}")
                            }
                        )

                        if (index >= list.size - 4) {
                            LaunchedEffect(key1 = index) {
                                viewModel.loadNextPage()
                            }
                        }
                    }

                    item(span = { GridItemSpan(2) }) {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}

