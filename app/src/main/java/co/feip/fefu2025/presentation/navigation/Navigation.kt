package co.feip.fefu2025.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.feip.fefu2025.presentation.details.AnimeScreenView
import co.feip.fefu2025.presentation.details.AnimeScreenViewModel
import co.feip.fefu2025.presentation.main.MainScreenView
import co.feip.fefu2025.presentation.main.MainScreenViewModel

@Composable
fun MainNavigation(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel,
    animeScreenViewModel: AnimeScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "animeList"
    ) {
        composable("animeList") {
            val screen = MainScreenView(mainScreenViewModel)
            screen.view(navController)
        }

        composable("anime/{animeId}") { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
            if (animeId != null) {
                AnimeScreenView(animeScreenViewModel, animeId, navController)
            }
        }
    }
}