package co.feip.fefu2025

import kotlin.math.roundToInt

fun  ratingCount(ratings : Map<Int, Int>): Double
{
    var Rating = 0.0
    var count = 0
    for(rating in ratings)
    {
        Rating += rating.key*rating.value
        count += rating.value
    }
    return ((Rating / count).roundToInt() * 100.0) / 100.0
}

