package co.feip.fefu2025.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import co.feip.fefu2025.presentation.details.utils.FlexBoxLayout
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel
import co.feip.fefu2025.presentation.recomendations.RecommendationsSectionView
import co.feip.fefu2025.presentation.state.UiState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeScreenView(
    animeScreenViewModel: AnimeScreenViewModel,
    id: Int?,
    navController: NavController,
    recommendationsScreenViewModel: RecomendationsScreenViewModel
) {
    val animeState = animeScreenViewModel.animeState

    LaunchedEffect(key1 = id) {
        animeScreenViewModel.loadAnimeData(id)

    }

    when (animeState) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ошибка: ${animeState.message}")
                    Button(onClick = { animeScreenViewModel.retry(id) }) {
                        Text("Повторить")
                    }
                }
            }
        }

        is UiState.Success -> {
            val anime = animeState.data
            val recs = animeScreenViewModel.recsState

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    TopAppBar(
                        title = { Text(anime.name) },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                            }
                        }
                    )
                }

                item {
                    Image(
                        painter = rememberAsyncImagePainter(anime.imageUrl),
                        contentDescription = anime.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(220.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                item { Text(anime.name, fontSize = 20.sp, fontWeight = FontWeight.Bold) }

                item {
                    AndroidView(
                        factory = { context ->
                            FlexBoxLayout(context).apply {
                                anime.genres.forEach { genre ->
                                    val genreView = AnimeGenreView(context).apply {
                                        setGenreName(genre)
                                    }
                                    addView(genreView)
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    anime.description?.let {
                        Text(it, fontSize = 14.sp, textAlign = TextAlign.Justify)
                    }
                }

                item {
                    Text("Рейтинг: ${anime.score}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                item {
                    RatingChartView(anime.ratingMap)
                }

                item {
                    Text("Год выпуска: ${anime.year}", fontSize = 16.sp)
                    Text("Эпизодов: ${anime.episodes}", fontSize = 16.sp)
                }

                item {
                    if (recs.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        RecommendationsSectionView(recs, navController, recommendationsScreenViewModel)
                    }
                }
            }
        }
    }
}