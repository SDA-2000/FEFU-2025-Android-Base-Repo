package co.feip.fefu2025.presentation.details

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
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.main.AnimeCard
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.presentation.details.utils.isDrawableResourceValid


@Composable
fun RecommendationsSectionView(recommendations: List<Anime?>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Может понравиться",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(recommendations) { anime ->
                anime?.let {
                    val validImageResId = if (isDrawableResourceValid(context, it.ImageResId)) {
                        it.ImageResId
                    } else {
                        R.drawable.here
                    }

                    AnimeCard(
                        title = it.name,
                        genres = it.genres,
                        imageResId = validImageResId,
                        viewers = it.grade,
                        modifier = Modifier.width(140.dp)
                    )
                }
            }
        }
    }
}



