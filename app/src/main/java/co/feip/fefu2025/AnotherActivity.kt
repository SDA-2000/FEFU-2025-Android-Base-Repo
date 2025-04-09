package co.feip.fefu2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

class AnotherActivity : ComponentActivity()
{
    override fun onCreate(sevedInstanceState : Bundle?)
    {
        super.onCreate(sevedInstanceState)
        setContent {
            FEFU2025AndroidBaseRepoTheme {
                Screen()
            }
        }
    }
}
@Composable
fun Screen()
{
    Text(text = "Activity")
}