package org.chiachat.app.web

import androidx.compose.runtime.Composable
import org.chiachat.app.ComposeApp
import org.chiachat.app.compose.components.shared.main.MainComponent
import kotlinx.browser.window
import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement

class WebApp {

  val app = ComposeApp()
  val mainComponent = MainComponent()

  val canvas = window.document.getElementById("ComposeTarget") as HTMLCanvasElement
  val ctx = canvas.getContext("webgl") as WebGLRenderingContext

  init {
    resizeCanvas()
    window.onresize = { resizeCanvas() }
  }

  fun resizeCanvas() {
    canvas.width = window.innerWidth
    canvas.height = window.innerHeight
    ctx.viewport(0, 0, window.innerWidth, window.innerHeight)
  }

  @Composable
  fun View() {
    mainComponent.view()
  }
}
