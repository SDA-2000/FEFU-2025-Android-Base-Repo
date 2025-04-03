package co.feip.fefu2025

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecommendationsSection(recommendations: List<AnimeCardModel>) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Может понравиться", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        LazyRow (horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            items(recommendations) { anime ->
                AnimeCard(anime.title, anime.genres, anime.imageResId, anime.viewers, modifier = Modifier.width(140.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RecommendationSectionPreview()
{
    val animeList = listOf(
        AnimeCardModel("Ковбой Бибоп", listOf("Приключения", "Драма", "Комедия"), R.drawable.cowboy_bebop, "9.1"),
        AnimeCardModel("Берсерк", listOf("Приключения", "Сейнен", "Экшен"), R.drawable.berserk, "8.9"),
        AnimeCardModel("Монстр", listOf("Детектив", "Драма", "Триллер"), R.drawable.monster, "9.3"),
        AnimeCardModel("Хеллсинг OVA", listOf("Экшен", "Сейнен"), R.drawable.hellsing_ova, "8.6"),
        AnimeCardModel("Ван Пис", listOf("Приключения", "Сёнен", "Комедия"), R.drawable.one_piece, "9.15"),
        AnimeCardModel("Пираты \" Чёрной Лагуны \"", listOf("Экшен", "Сейнен"), R.drawable.black_lagoon, "8.5"),
        AnimeCardModel("Триган", listOf("Приключения", "Драма", "Комедия"), R.drawable.trigun, "8.8"),
        AnimeCardModel("Покемоны", listOf("Приключения", "Сёнен"), R.drawable.pokemon, "7.4"),
        AnimeCardModel("Психо-пасспорт", listOf("Мистика", "Драма", "Детектив"), R.drawable.psycho_pass, "8.1"),
        AnimeCardModel("Детектив Конан", listOf("Детектив", "Комедия"), R.drawable.conan_detective, "7.8")
    )

    RecommendationsSection(animeList)

}

