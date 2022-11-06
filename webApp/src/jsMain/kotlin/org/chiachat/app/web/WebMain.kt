package org.chiachat.app.web

import BrowserViewportWindow
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  onWasmReady {
    val app = WebApp()
    BrowserViewportWindow("ChiaChat") { app.View() }
  }
}
