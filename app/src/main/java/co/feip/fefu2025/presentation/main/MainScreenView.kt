package co.feip.fefu2025.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.details.utils.isDrawableResourceValid
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel


@Composable
fun MainScreenView(
    viewModel: MainScreenViewModel,
    navController: NavController,
    recommendationsScreenViewModel: RecomendationsScreenViewModel
) {
    val animeList = viewModel.animeList
    val isLoading = viewModel.isLoading
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(animeList) { anime ->
                    val validImageResId = if (isDrawableResourceValid(context, anime.ImageResId)) {
                        anime.ImageResId
                    } else {
                        R.drawable.here
                    }

                    AnimeCardView(
                        id = anime.id,
                        title = anime.name,
                        genres = anime.genres,
                        imageResId = validImageResId,
                        viewers = anime.grade,
                        modifier = Modifier,
                        onClick = {
                            navController.navigate("anime/${anime.id}")
                        }
                    )
                }
            }
        }
    }
}
