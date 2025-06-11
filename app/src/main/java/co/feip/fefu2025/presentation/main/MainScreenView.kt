package co.feip.fefu2025.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.details.utils.isDrawableResourceValid


class MainScreenView(private val mainScreenViewModel: MainScreenViewModel){
    private val animelist = mainScreenViewModel.GetAnimeList()
    @Composable
    fun view()
    {

        val context = LocalContext.current
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
                items(animelist) { anime ->
                    anime.let {
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
                            modifier = Modifier
                        )
                }
            }
        }
    }

}}