package org.chiachat.app.compose.components

import androidx.compose.runtime.Composable

interface Component {
  val vm: IViewModel
  @Composable fun View()
}
