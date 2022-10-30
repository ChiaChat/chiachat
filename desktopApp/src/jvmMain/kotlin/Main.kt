import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chiachat.app.ComposeApp
import org.chiachat.app.compose.components.shared.main.MainComponent
import org.chiachat.app.compose.theme.ChiaChatTheme

val app = ComposeApp()
val mainComponent = MainComponent()

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
            mainComponent.view()
    }
}
