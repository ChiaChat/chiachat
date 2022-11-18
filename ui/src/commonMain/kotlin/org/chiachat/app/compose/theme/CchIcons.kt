package org.chiachat.app.compose.theme

import androidx.compose.ui.graphics.ImageBitmap
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chiachat.app.compose.util.loadImageBitmap
import org.chiachat.app.compose.util.readImageBitmap

enum class CchIcons(val file: VfsFile) {
    DARK_MODE(resourcesVfs["icons/material/dark_mode.xml"]),
    LIGHT_MODE(resourcesVfs["icons/material/light_mode.xml"]);
}