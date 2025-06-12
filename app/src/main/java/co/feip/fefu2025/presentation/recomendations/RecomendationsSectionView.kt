package co.feip.fefu2025.presentation.recomendations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.main.AnimeCardView
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.presentation.details.utils.isDrawableResourceValid


@Composable
fun RecommendationsSectionView(
    recommendations: List<Anime?>,
    navController: NavController,
    recomendationsScreenViewModel: RecomendationsScreenViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Может понравиться",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                recomendationsScreenViewModel.setRecommendations(recommendations.filterNotNull())
                navController.navigate("recommendations")
            }
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(recommendations) { anime ->
                anime?.let {
                    val validImageResId = if (isDrawableResourceValid(context, it.ImageResId)) {
                        it.ImageResId
                    } else {
                        R.drawable.here
                    }

                    AnimeCardView(
                        id = it.id,
                        title = it.name,
                        genres = it.genres,
                        imageResId = validImageResId,
                        viewers = it.grade,
                        modifier = Modifier.width(140.dp),
                        onClick = {
                            navController.navigate("anime/${anime.id}")
                        }
                    )
                }
            }
        }
    }
}




