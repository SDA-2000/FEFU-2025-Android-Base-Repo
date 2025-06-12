package co.feip.fefu2025.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.feip.fefu2025.presentation.details.AnimeScreenView
import co.feip.fefu2025.presentation.details.AnimeScreenViewModel
import co.feip.fefu2025.presentation.main.MainScreenView
import co.feip.fefu2025.presentation.main.MainScreenViewModel
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel
import co.feip.fefu2025.presentation.recommendations.RecommendationsScreenView

@Composable
fun MainNavigation(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel,
    animeScreenViewModel: AnimeScreenViewModel,
    recommendationsScreenViewModel: RecomendationsScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            val screen = MainScreenView(mainScreenViewModel)
            screen.view(navController, recommendationsScreenViewModel)
        }

        composable("anime/{animeId}") { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
            if (animeId != null) {
                AnimeScreenView(animeScreenViewModel, animeId, navController, recommendationsScreenViewModel)
            }
        }

        composable("recommendations") {
            RecommendationsScreenView(
                navController = navController,
                sharedViewModel = recommendationsScreenViewModel
            )
        }
    }
}
