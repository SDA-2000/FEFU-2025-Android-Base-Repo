package co.feip.fefu2025

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(animeList: List<AnimeCardModel>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Поиск аниме...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(animeList) { anime ->
                AnimeCard(anime.title, anime.genres, anime.imageResId, anime.viewers, modifier = Modifier)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview()
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
        AnimeCardModel("Детектив Конан", listOf("Детектив", "Комедия"), R.drawable.conan_detective, "7.8"))

    MainScreen(animeList)
}
