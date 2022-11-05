package org.chiachat.app.web

import BrowserViewportWindow
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  console.log("Test")
  onWasmReady {
    val app = WebApp()
    BrowserViewportWindow("ChiaChat") { app.View() }
  }
}
