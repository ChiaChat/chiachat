import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeApp
import org.chiachat.app.compose.ComposeAppModules
import org.koin.core.context.startKoin

fun main() {
  startKoin { modules(SharedAppModules.sharedModule, ComposeAppModules.composeModule) }
  val mainComponent = ComposeApp()
  application { Window(onCloseRequest = ::exitApplication) { mainComponent.View() } }
}
