package org.chiachat.app.web

import BrowserViewportWindow
import org.chiachat.app.WebRoot
import org.jetbrains.skiko.wasm.onWasmReady


fun main() {
  onWasmReady {
    val app = WebRoot()
    BrowserViewportWindow("ChiaChat") { app.View() }
  }
}
