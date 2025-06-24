package co.feip.fefu2025.presentation.recomendations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.feip.fefu2025.presentation.main.AnimeCardView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationsScreenView(
    navController: NavController,
    sharedViewModel: RecomendationsScreenViewModel,
    animeId: Int
) {
    val recommendations by sharedViewModel.recs

    LaunchedEffect(animeId) {
        sharedViewModel.loadInitial(animeId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Может понравиться") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(recommendations) { anime ->
                AnimeCardView(
                    id = anime.id,
                    title = anime.name,
                    genres = anime.genres,
                    imageUrl = anime.imageUrl,
                    viewers = anime.score.toString(),
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        navController.navigate("anime/${anime.id}")
                    }
                )
            }

            item {
                LaunchedEffect(Unit) {
                    sharedViewModel.loadNextPage()
                }
            }
        }
    }
}