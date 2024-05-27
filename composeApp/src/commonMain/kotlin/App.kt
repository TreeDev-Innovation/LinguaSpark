import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

import linguaspark.composeapp.generated.resources.Res
import linguaspark.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var yakasDich by remember { mutableStateOf(true) }
        LaunchedEffect(yakasDich) {
            if (yakasDich.not()) {
                delay(500)
                yakasDich = true
            }
        }

        AnimatedVisibility(yakasDich,
            enter = scaleIn() + fadeIn(),
            exit = slideOutHorizontally(animationSpec = tween(delayMillis = 500)) { +500 } + slideOutHorizontally(animationSpec = tween(delayMillis = 1000)) { -500 } + slideOutHorizontally(animationSpec = tween(delayMillis = 1500)) { +500 } + slideOutHorizontally(animationSpec = tween(delayMillis = 2000)) { -500 } + slideOutHorizontally(animationSpec = tween(delayMillis = 2500)) { +500 } + slideOutHorizontally() { -500 } + slideOutHorizontally(animationSpec = tween(delayMillis = 3000)) { +500 } + slideOutHorizontally() { -500 }
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")

            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
                Button(onClick = { yakasDich = !yakasDich }) {
                    Text("Montana this is the mountain")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
        }
    }
}
