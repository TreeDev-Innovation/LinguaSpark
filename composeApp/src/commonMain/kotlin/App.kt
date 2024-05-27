import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
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


    // router
    val navController = rememberNavController()

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                MainApp(navController = navController)
            }
            BottomNavigation {
                var selected by remember { mutableStateOf(0) }
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selected == 0,
                    onClick = {
                        navController.navigate(NavigationDestinations.HOME.route)
                        selected = 0
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selected == 1,
                    onClick = {
                        navController.navigate(NavigationDestinations.PROFILE.route)
                        selected = 1
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selected == 2,
                    onClick = {
                        navController.navigate(NavigationDestinations.SETTINGS.route)
                        selected = 2
                    }
                )
                BottomNavigationItem(
                    modifier = Modifier.background(MaterialTheme.colors.secondary),
                    icon = { Icon(Icons.Filled.Info, contentDescription = "About") },
                    label = { Text("About") },
                    selected = selected == 3,
                    onClick = {
                        navController.navigate(NavigationDestinations.ABOUT.route)
                        selected = 3
                    }
                )
            }

        }
    }
}

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(NavigationDestinations.HOME.route) {
            HomeScreen(navController)
        }
        composable(NavigationDestinations.PROFILE.route) {
            ProfileScreen(navController)
        }
        composable(NavigationDestinations.SETTINGS.route) {
            SettingsScreen(navController)
        }
        composable(NavigationDestinations.ABOUT.route) {
            AboutScreen(navController)
        }

    }
}

@Composable
fun HomeScreen(navController: NavController) {
    // i need to center text in the middle of the screen
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text("Home Screen")
    }

}

@Composable
fun ProfileScreen(navController: NavController) {
    // box with row of two text elements
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Text("Profile Screen 1")
            Text("Profile Screen 2")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Text("Settings Screen")
}

@Composable
fun AboutScreen(navController: NavController) {
    Text("About Screen")
}

enum class NavigationDestinations(val route: String) {
    HOME("home"),
    PROFILE("profile"),
    SETTINGS("settings"),
    ABOUT("about"),
}
