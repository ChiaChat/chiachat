package org.chiachat.app.compose.components

import androidx.compose.runtime.Composable

abstract class Component {
  @Composable abstract fun view()
}
