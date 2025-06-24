package co.feip.fefu2025.domain.entities

data class Anime(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val year : Int,
    val imageUrl: String = "",
    val description: String? = null,
    val score : Double,
    val episodes: Int,
    val recommendations: List<Int>
)
{
    val ratingMap : Map<Int, Int> = mapOf(
        1 to  1,
        2 to  1,
        3 to  1,
        4 to  1,
        5 to  1,
        6 to  1,
        7 to  1,
        8 to  1,
        9 to  1,
        10 to 10);
}