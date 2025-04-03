package co.feip.fefu2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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

@Composable
fun AnimeScreen(
    imageUrl: String,
    title: String,
    genres: List<String>,
    description: String,
    rating: String,
    year: String,
    episodes: String
) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (imageUrl.isNullOrEmpty()) {
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

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


        Spacer(modifier = Modifier.height(8.dp))
        Text(text = description, fontSize = 14.sp, textAlign = TextAlign.Justify)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Рейтинг: $rating", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "Год выпуска: $year", fontSize = 16.sp)
        Text(text = "Эпизодов: $episodes", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimeScreen() {
    AnimeScreen(
        imageUrl = "",
        title = "Карманы Лета",
        genres = listOf("Повседневность"),
        description = "Во время летних каникул Хаири Такахара отправился на остров Торисиродзима, чтобы разобрать имущество недавно умершей бабушки...",
        rating = "8.5",
        year = "2023",
        episodes = "12"
    )
}