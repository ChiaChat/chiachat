package org.chiachat.app.desktop

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
  val desktopApp = DesktopApp()

  application {
    val icon = painterResource("icons/chiachat-trans-256x256.png")
    Window(onCloseRequest = ::exitApplication, icon = icon) { desktopApp.composeRoot.View() }
  }
}
