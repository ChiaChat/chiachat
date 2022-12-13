package org.chiachat.app.ui.services

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.ui.theme.CchGraphics
import org.chiachat.app.util.readImageBitmap

class ResourceService(private val ioScope: CoroutineScope) {

  private val iconCache = MutableStateFlow<Map<CchGraphics, ImageBitmap>>(emptyMap())

  fun loadGraphic(icon: CchGraphics, onLoad: (ImageBitmap) -> Unit) {
    val cachedIcon = iconCache.value[icon]
    if (cachedIcon != null) {
      onLoad(cachedIcon)
    } else {
      ioScope.launch {
        val bitmap = icon.file.readImageBitmap()
        iconCache.value += icon to bitmap
        onLoad(bitmap)
      }
    }
  }

  fun clearCache() {
    iconCache.value = emptyMap()
  }
}
