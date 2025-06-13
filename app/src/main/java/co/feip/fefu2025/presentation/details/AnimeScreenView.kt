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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import co.feip.fefu2025.presentation.details.utils.FlexBoxLayout
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.details.utils.isDrawableResourceValid
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel
import co.feip.fefu2025.presentation.recomendations.RecommendationsSectionView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeScreenView(
    animeScreenViewModel: AnimeScreenViewModel,
    id: Int,
    navController: NavController,
    recommendationsScreenViewModel: RecomendationsScreenViewModel
) {
    val context = LocalContext.current
    val anime = animeScreenViewModel.anime
    val recommendations = animeScreenViewModel.recommendations
    val isLoading = animeScreenViewModel.isLoading

    LaunchedEffect(key1 = id) {
        animeScreenViewModel.loadAnimeData(id)
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (anime == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Аниме не найдено")
        }
    } else {
        val imageResId = if (isDrawableResourceValid(context, anime.ImageResId)) {
            anime.ImageResId
        } else {
            R.drawable.here
        }

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
                        IconButton(onClick = { navController.navigate("main") }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                        }
                    }
                )
            }
            item {
                Image(
                    painter = painterResource(id = imageResId),
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
                    Text(text = it, fontSize = 14.sp, textAlign = TextAlign.Justify)
                }
            }
            item { Text("Рейтинг: ${anime.grade}", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
            item { RatingChartView(anime.RatingMap) }
            item {
                Text("Год выпуска: ${anime.year}", fontSize = 16.sp)
                Text("Эпизодов: ${anime.Episodes}", fontSize = 16.sp)
            }

            item {
                RecommendationsSectionView(
                    recommendations,
                    navController,
                    recommendationsScreenViewModel
                )
            }
        }
    }
}
