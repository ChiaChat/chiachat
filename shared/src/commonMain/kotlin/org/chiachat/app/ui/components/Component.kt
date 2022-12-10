package org.chiachat.app.ui.components

import androidx.compose.runtime.Composable
import org.koin.core.component.KoinComponent

internal interface Component: KoinComponent {
  val vm: IViewModel
  @Composable fun View()
}
