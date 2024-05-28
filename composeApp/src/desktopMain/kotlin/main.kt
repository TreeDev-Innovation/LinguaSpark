import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.appModule
import di.startDI
import org.koin.core.context.startKoin

fun main() = application {
    startDI()
    Window(
        onCloseRequest = ::exitApplication,
        title = "LinguaSpark",
    ) {
        App()
    }
}