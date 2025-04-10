package co.feip.fefu2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import kotlin.collections.listOf

@Composable
fun AnimeScreen(
    imageUrl: String,
    title: String,
    genres: List<String>,
    description: String,
    year: String,
    episodes: String,
    recomendations: List<AnimeCardModel>,
    ratings: Map<Int, Int>
) {
    val rating = ratingCount(ratings)
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            if (imageUrl.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.ic145488),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(220.dp).fillMaxWidth()
                )
            } else {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(220.dp).fillMaxWidth()
                )
            }
        }
        item {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        item {
            AndroidView(
                factory = { context ->
                    FlexBoxLayout(context).apply {
                        genres.forEach { genre ->
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
            Text(text = description, fontSize = 14.sp, textAlign = TextAlign.Justify)
        }
        item {
            Text(text = "Рейтинг: $rating", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        item {
            RatingChart(ratings)
        }
        item {
            Text(text = "Год выпуска: $year", fontSize = 16.sp)
            Text(text = "Эпизодов: $episodes", fontSize = 16.sp)
        }
        item {
            RecommendationsSection(recomendations)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAnimeScreen() {
    val sampleRatings = mapOf(
        1 to 100,
        2 to 50,
        3 to 200,
        4 to 300,
        5 to 250,
        6 to 400,
        7 to 500,
        8 to 600,
        9 to 450,
        10 to 700
    )

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

    AnimeScreen(
        imageUrl = "",
        title = "Карманы Лета",
        genres = listOf("Повседневность"),
        description = "Во время летних каникул Хаири Такахара отправился на остров Торисиродзима, чтобы разобрать имущество недавно умершей бабушки...",
        year = "2023",
        episodes = "12",
        ratings = sampleRatings,
        recomendations = animeList
    )
}