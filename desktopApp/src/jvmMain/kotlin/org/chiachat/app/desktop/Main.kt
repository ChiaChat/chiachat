package org.chiachat.app.desktop

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.runBlocking
import org.chiachat.app.db.PlatformDb

fun main() {
  val sqlDriver = runBlocking { PlatformDb().getDriver() }
  val desktopApp = DesktopApp(sqlDriver)
  application {
    val icon = painterResource("icons/chiachat/chiachat-trans-256x256.png")
    Window(onCloseRequest = ::exitApplication, icon = icon) { desktopApp.composeRoot.View() }
  }
}
