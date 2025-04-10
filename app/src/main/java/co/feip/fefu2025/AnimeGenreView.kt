package co.feip.fefu2025

import android.content.Context
import androidx.compose.ui.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb

class AnimeGenreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)
{
    private val tvGenreName: TextView

    init
    {
        LayoutInflater.from(context).inflate(R.layout.anime_genre_view_layout, this, true)
        tvGenreName = findViewById(R.id.genreNameView)
    }

    fun setGenreName(name: String) {
        tvGenreName.text = name
        val genreColor = genreColors[name] ?: Color.Gray.toArgb()
        setGenreBackgroundColor(genreColor)
    }

    fun setGenreBackgroundColor(colorInt: Int) {
        val drawable = background as? GradientDrawable ?: GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 16f
        }
        drawable.setColor(colorInt)
        background = drawable
        tvGenreName.setTextColor(Color.White.toArgb())
    }
}

