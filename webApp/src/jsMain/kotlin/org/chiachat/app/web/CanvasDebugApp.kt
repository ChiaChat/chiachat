package org.chiachat.app.web

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow
import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement

class CanvasDebugApp {
    val canvas = window.document.getElementById("ComposeTarget") as HTMLCanvasElement
    val ctx = canvas.getContext("webgl") as WebGLRenderingContext
  val div = window.document.body?.firstElementChild

  val screenInfo = MutableStateFlow(ScreenInfo(0, 0, 0, 0, 0, 0, 0, 0, 0.0))

  init {
    resizeCanvas()
    window.onresize = { resizeCanvas() }
  }

  fun resizeCanvas() {
    canvas.width = window.innerWidth
    canvas.height = window.innerHeight
    ctx.viewport(0, 0, window.innerWidth, window.innerHeight)
    screenInfo.value =
        ScreenInfo(
            canvas.width,
            canvas.height,
            canvas.clientWidth,
            canvas.clientHeight,
            window.innerWidth,
            window.innerHeight,
            ctx.drawingBufferWidth,
            ctx.drawingBufferHeight,
            window.devicePixelRatio)
    console.log("Resizing, ${canvas.width}, ${canvas.height}")
  }

  @Composable
  fun View() {
    val si by screenInfo.collectAsState()
    Column(
        modifier = Modifier.background(Color.Black).fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
          Debug(si)
          Spacer(modifier = Modifier.size(20.dp))
          Text("Hello World", fontWeight = FontWeight.W500, color = Color.Cyan)
        }
  }

  @Composable
  fun Debug(si: ScreenInfo) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
      Text("Canvas - ${si.cw}, ${si.ch}", fontWeight = FontWeight.W500, color = Color.Cyan)
      Text("Canvas Client - ${si.ccw}, ${si.cch}", fontWeight = FontWeight.W500, color = Color.Cyan)
      Text("Window - ${si.ww}, ${si.wh}", fontWeight = FontWeight.W500, color = Color.Cyan)
      Text(
          "Drawing Buffer - ${si.dbw}, ${si.dbh}", fontWeight = FontWeight.W500, color = Color.Cyan)
      Text("DPR - ${si.dpr}", fontWeight = FontWeight.W500, color = Color.Cyan)
    }
  }
}

data class ScreenInfo(
    val cw: Int,
    val ch: Int,
    val ccw: Int,
    val cch: Int,
    val ww: Int,
    val wh: Int,
    val dbw: Int,
    val dbh: Int,
    val dpr: Double,
)
