package co.feip.fefu2025

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addButton)
        val flexBoxLayout = findViewById<FlexBoxLayout>(R.id.flexLayout)

        addButton.setOnClickListener{
            val genreName = AnimeGenres.genres.random()
            val color = Colors.colors.random().toColorInt()

            val genreNameView = AnimeGenreView(this)
            genreNameView.setGenreName(genreName)
            genreNameView.setGenreBackgroundColor(color)
            flexBoxLayout.addView(genreNameView)
        }
    }
}