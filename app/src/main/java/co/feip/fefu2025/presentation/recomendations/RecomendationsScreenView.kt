package co.feip.fefu2025.presentation.recommendations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.feip.fefu2025.presentation.main.AnimeCardView
import androidx.compose.ui.platform.LocalContext
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.details.utils.isDrawableResourceValid
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationsScreenView(
    navController: NavController,
    sharedViewModel: RecomendationsScreenViewModel
) {
    val recommendations = sharedViewModel.recommendations ?: emptyList()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Может понравиться") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(recommendations) { anime ->
                val imageResId = if (isDrawableResourceValid(context, anime.ImageResId)) {
                    anime.ImageResId
                } else {
                    R.drawable.here
                }

                AnimeCardView(
                    id = anime.id,
                    title = anime.name,
                    genres = anime.genres,
                    imageResId = imageResId,
                    viewers = anime.grade,
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        navController.navigate("anime/${anime.id}")
                    }
                )
            }
        }
    }
}