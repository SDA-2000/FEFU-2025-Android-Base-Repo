package co.feip.fefu2025.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.presentation.details.utils.FlexBoxLayout
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.details.utils.isDrawableResourceValid


@Composable
fun AnimeScreenView(
    animeScreenViewModel: AnimeScreenViewModel, id : Int
) {
    var anime = animeScreenViewModel.LoadAnimeById(id)
    val context = LocalContext.current

    if(anime == null)
    {
        Text("Не найдено")
    }
    else
    {
        val imageResId = if (anime.ImageResId != null &&
            isDrawableResourceValid(context, anime.ImageResId)) {
            anime.ImageResId
        } else {
            R.drawable.here
        }
        val rating = anime.grade
        val recs = animeScreenViewModel.LoadRecsById(anime.Recomendations)

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                    Image(painter = painterResource(id = imageResId),
                        contentDescription = anime.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(220.dp).fillMaxWidth()
                    )
            }
            item {
                Text(text = anime.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
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
                anime.description?.let { Text(text = it, fontSize = 14.sp, textAlign = TextAlign.Justify) }
            }
            item {
                Text(text = "Рейтинг: $rating", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            item {
                RatingChartView(anime.RatingMap)
            }
            item {
                Text(text = "Год выпуска: ${anime.year}", fontSize = 16.sp)
                Text(text = "Эпизодов: ${anime.Episodes}", fontSize = 16.sp)
            }
            item {
                RecommendationsSectionView(recs)
            }
        }
    }


}