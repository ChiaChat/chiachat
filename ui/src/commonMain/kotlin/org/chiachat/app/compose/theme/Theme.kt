package org.chiachat.app.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.flow.MutableStateFlow

val oxygenFonts =
    listOf("Oxygen-Bold", "Oxygen-Light", "Oxygen-Regular")
        .map { "fonts/$it.ttf" }
        .map { resourcesVfs[it] }

suspend fun loadOxygen(): FontFamily = FontFamily(oxygenFonts.map { loadFont(it) })


suspend fun loadThemeResources(): ThemeResources {
    return ThemeResources(
        defaultFont = loadOxygen()
    )
}

@Composable
fun ChiaChatTheme(resources: ThemeResources, content: @Composable () -> Unit) {
  MaterialTheme(
      colors = if (isSystemInDarkTheme()) chiachatDarkScheme else chiachatDarkScheme,
      typography = ChiaChatTypography(resources),
      content = content)
}
