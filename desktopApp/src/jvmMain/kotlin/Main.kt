import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeApp
import org.chiachat.app.compose.ComposeAppModules
import org.koin.core.context.startKoin

fun main() {
  startKoin { modules(SharedAppModules.sharedModule, ComposeAppModules.composeModule) }
  val mainComponent = ComposeApp()
  application {
    val icon = painterResource("icons/chiachat-trans-256x256.png")
    Window(onCloseRequest = ::exitApplication, icon = icon) { mainComponent.View() }
  }
}
