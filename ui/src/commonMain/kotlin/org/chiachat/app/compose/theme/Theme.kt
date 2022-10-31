package org.chiachat.app.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import com.soywiz.korio.file.std.resourcesVfs

val oxygenFonts =
    listOf("Oxygen-Bold", "Oxygen-Light", "Oxygen-Regular")
        .map { "fonts/$it.ttf" }
        .map { resourcesVfs[it] }

suspend fun loadOxygen(): FontFamily = FontFamily(oxygenFonts.map { loadFont(it) })

suspend fun loadThemeResources(): ThemeResources {
  return ThemeResources(defaultFont = loadOxygen())
}

@Composable
fun ChiaChatTheme(resources: ThemeResources, content: @Composable () -> Unit) {
  MaterialTheme(
      colorScheme = if (isSystemInDarkTheme()) matteBlackDarkScheme else  matteBlackLightScheme,
//      typography = ChiaChatTypography(resources),
      content = content)
}
