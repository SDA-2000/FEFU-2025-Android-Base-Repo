package co.feip.fefu2025.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import co.feip.fefu2025.presentation.details.AnimeScreenView
import co.feip.fefu2025.presentation.details.AnimeScreenViewModel
import co.feip.fefu2025.presentation.main.MainScreenView
import co.feip.fefu2025.presentation.main.MainScreenViewModel
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel
import co.feip.fefu2025.presentation.recommendations.RecommendationsScreenView
import co.feip.fefu2025.presentation.search.SearchScreenView
import co.feip.fefu2025.presentation.search.SearchScreenViewModel

@Composable
fun MainNavigation(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel,
    animeScreenViewModel: AnimeScreenViewModel,
    recommendationsScreenViewModel: RecomendationsScreenViewModel,
    searchScreenViewModel: SearchScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreenView(mainScreenViewModel, navController, recommendationsScreenViewModel)
        }

        composable(
            route = "anime/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink { uriPattern = "mysuperapp://anime/{id}" })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("id")
            if (animeId != -1) {
                AnimeScreenView(animeScreenViewModel,
                    animeId, navController, recommendationsScreenViewModel)
            }
        }

        composable("recommendations") {
            RecommendationsScreenView(
                navController = navController,
                sharedViewModel = recommendationsScreenViewModel
            )
        }

        composable("search") {
            SearchScreenView(navController = navController, searchScreenViewModel)
        }

    }
}