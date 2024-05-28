import androidx.compose.animation.AnimatedVisibility
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource

import linguaspark.composeapp.generated.resources.Res
import linguaspark.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainApp()
    }
}

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainAppNavigation.CountrySelector.name
    ) {
        composable(MainAppNavigation.CountrySelector.name) {
            CountrySelector()
        }
        composable(MainAppNavigation.Home.name) {
            HomeScreen(navController)
        }
        composable(MainAppNavigation.Login.name) {
            LoginScreen(navController)
        }
    }
}

enum class MainAppNavigation { CountrySelector, Home, Login }

@Composable
fun HomeScreen(navController: NavHostController) {

}

@Composable
fun LoginScreen(navController: NavHostController) {

}
