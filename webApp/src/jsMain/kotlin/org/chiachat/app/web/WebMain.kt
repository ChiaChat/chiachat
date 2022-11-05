package org.chiachat.app.web

import androidx.compose.ui.window.Window
import org.jetbrains.skiko.wasm.onWasmReady
import org.chiachat.app.web.BrowserViewportWindow

fun main() {
  console.log("Test")
  onWasmReady {
    val app = WebApp()
    BrowserViewportWindow("ChiaChat") {
      app.View()
    }
  }
}
