package org.chiachat.app.ui.components

import androidx.compose.runtime.Composable

internal interface Component {
  val vm: IViewModel
  @Composable fun View()
}
