package org.chiachat.app.compose.services

import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.app.util.MpSettings

class ThemeService(settings: MpSettings) {
    val darkMode = MutableStateFlow(settings.darkMode)

    fun toggleDarkTheme(){
        darkMode.value = !darkMode.value
    }
}