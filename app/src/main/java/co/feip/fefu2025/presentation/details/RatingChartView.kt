package co.feip.fefu2025.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingChartView(ratings: Map<Int, Int>) {
    val maxRating = ratings.values.maxOrNull() ?: 1
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        ratings.forEach { (score, count) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height((100 * count / maxRating).dp)
                        .background(
                            when (score) {
                                1 -> Color.Red
                                2 -> Color(0xFFFF4500)
                                3 -> Color(0xFFFFA500)
                                4 -> Color(0xFFFF8C00)
                                5 -> Color.Yellow
                                6 -> Color(0xFF65C421)
                                7 -> Color(0xFF84BD47)
                                8 -> Color(0xFF63AF34)
                                9 -> Color(0xFF33C733)
                                10 -> Color.Green
                                else -> Color.Gray
                            }
                        )
                )
                Text(text = count.toString(), fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
@Preview(showBackground = true)
fun RatingChartPrewiew()
{
    val sampleRatings = mapOf(
        1 to 100,
        2 to 50,
        3 to 200,
        4 to 300,
        5 to 250,
        6 to 400,
        7 to 500,
        8 to 600,
        9 to 450,
        10 to 700
    )
    RatingChartView(ratings = sampleRatings)
}