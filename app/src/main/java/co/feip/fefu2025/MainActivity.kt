package co.feip.fefu2025

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addButton)
        val flexBoxLayout = findViewById<FlexBoxLayout>(R.id.flexLayout)

        addButton.setOnClickListener{
            val genreName = genreColors.keys.random()
            val color = genreColors.values.random()
            val genreNameView = AnimeGenreView(this)
            genreNameView.setGenreName(genreName)
            genreNameView.setGenreBackgroundColor(color)
            flexBoxLayout.addView(genreNameView)
        }
    }
}