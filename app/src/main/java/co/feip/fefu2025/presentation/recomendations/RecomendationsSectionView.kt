package co.feip.fefu2025.presentation.recomendations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.presentation.main.AnimeCardView


@Composable
fun RecommendationsSectionView(
    recommendations: List<Anime>,
    navController: NavController,
    animeId: Int?
) {
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
                navController.navigate("recommendations/$animeId")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(recommendations.take(10)) { anime ->
                AnimeCardView(
                    id = anime.id,
                    title = anime.name,
                    genres = anime.genres,
                    imageUrl = anime.imageUrl,
                    viewers = anime.score.toString(),
                    modifier = Modifier.width(140.dp),
                    onClick = {
                        navController.navigate("anime/${anime.id}")
                    }
                )
            }
        }
    }
}