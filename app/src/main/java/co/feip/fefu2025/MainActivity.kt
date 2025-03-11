package co.feip.fefu2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {

    private val counterKey = "counter_key"

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        counter = savedInstanceState?.getInt(counterKey) ?: 0

        setContent {
            FEFU2025AndroidBaseRepoTheme {
                MainScreen(counter = counter) { newCounter ->
                    counter = newCounter
                }
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(counterKey, counter)
    }
}

@Composable
fun MainScreen(counter: Int, onCounterChange: (Int) -> Unit) {
    var count by remember { mutableStateOf(counter) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Counter: $count",
            modifier = Modifier.clickable {
                count++
                onCounterChange(count)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context, AnotherActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Open Second Activity")
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    FEFU2025AndroidBaseRepoTheme {
        MainScreen(counter = 0) { }
    }
}

