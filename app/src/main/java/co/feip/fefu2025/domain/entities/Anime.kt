package co.feip.fefu2025.domain.entities

import co.feip.fefu2025.R
import java.math.BigDecimal
import java.math.RoundingMode

data class Anime(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val year : Int,
    var ImageResId: Int = R.drawable.here,
    val description: String? = null,
    val Episodes: Int,
    val RatingMap: Map<Int, Int>,
    val Recomendations: List<Int>
)
{
    val grade = gradeGet(RatingMap)
    fun gradeGet(RatingMap: Map<Int, Int>) : String
    {
        var sumation = 0.0
        var count = 0

        RatingMap.forEach { (key, value) -> sumation += key*value}
        RatingMap.forEach{ (key, value) -> count+=value}

        sumation /= count
        return BigDecimal(sumation.toString()).setScale(2, RoundingMode.CEILING).toString()
    }
}