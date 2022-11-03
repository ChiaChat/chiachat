package org.chiachat.app.web

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModule
import org.chiachat.app.compose.ComposeRoot
import org.khronos.webgl.WebGLRenderingContext
import org.koin.core.context.startKoin
import org.w3c.dom.HTMLCanvasElement

class WebApp {

  val mainComponent = ComposeRoot()

  val canvas = window.document.getElementById("ComposeTarget") as HTMLCanvasElement
  val ctx = canvas.getContext("webgl") as WebGLRenderingContext

  val ioScope = CoroutineScope(Dispatchers.Default)

  init {
    startKoin{
      modules(SharedAppModules.sharedModule, ComposeAppModule.composeModule)
      allowOverride(false)
    }
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
    mainComponent.View()
  }
}
