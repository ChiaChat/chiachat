package org.jetbrains.compose.demo.widgets

import DesktopRoot
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() {
    val desktopRoot = DesktopRoot()
    singleWindowApplication(title = "ChiaChat", state = WindowState(size = DpSize(800.dp, 600.dp))) {
        desktopRoot.View()
    }
}