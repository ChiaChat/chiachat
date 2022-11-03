package org.chiachat.app.compose.components

import androidx.compose.runtime.Composable
import org.chiachat.app.compose.services.NavigationService
import org.chiachat.app.toast.ToastService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Component {
  @Composable abstract fun view()
}
