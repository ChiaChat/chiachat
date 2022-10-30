package org.chiachat.app.compose.components.shared.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.chiachat.app.compose.components.Component
import org.chiachat.app.compose.theme.ChiaChatTheme
import org.chiachat.app.toast.ToastType
import org.koin.core.component.KoinComponent

class MainComponent : Component(), KoinComponent {

  @Composable
  override fun view() {
    ChiaChatTheme {
      Box(modifier = Modifier.fillMaxSize()) {
        val toast by toastService.currentToast.collectAsState()
        toast?.let {
          val toastColor =
              when (it.type) {
                ToastType.ERROR -> MaterialTheme.colors.error
                ToastType.SUCCESS -> MaterialTheme.colors.primary
                ToastType.WARNING -> Color.Yellow
                ToastType.DEBUG -> Color.Blue
                else -> MaterialTheme.colors.onSurface
              }
          Box(
              modifier =
                  Modifier.align(Alignment.TopCenter)
                      .background(MaterialTheme.colors.background)
                      .fillMaxWidth()
                      .padding(16.dp)) {
                Text("${it.type}: ${it.message}", color = toastColor)
              }
        }
        navigationService.currentView()
      }
    }
  }
}
