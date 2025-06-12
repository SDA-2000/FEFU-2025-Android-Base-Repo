package co.feip.fefu2025.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import co.feip.fefu2025.presentation.details.AnimeScreenViewModel
import co.feip.fefu2025.presentation.main.MainScreenViewModel
import co.feip.fefu2025.presentation.navigation.MainNavigation
import co.feip.fefu2025.presentation.recomendations.RecomendationsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{

    private  val mainScreenViewModel :MainScreenViewModel by viewModels()
    private  val animeScreenViewModel : AnimeScreenViewModel by viewModels()
    private val recomendationsScreenViewModel : RecomendationsScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainNavigation(navController, mainScreenViewModel, animeScreenViewModel, recomendationsScreenViewModel)
        }
    }
}
