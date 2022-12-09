package org.chiachat.app.ui.services

import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.app.util.MpSettings

class ThemeService(settings: MpSettings) {
  val darkMode = MutableStateFlow(settings.darkMode)

  fun toggleDarkTheme() {
    darkMode.value = !darkMode.value
  }
}
