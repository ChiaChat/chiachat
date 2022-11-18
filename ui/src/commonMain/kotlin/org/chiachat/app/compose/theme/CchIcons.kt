package org.chiachat.app.compose.theme

import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs

enum class CchIcons(val file: VfsFile) {
  DARK_MODE(resourcesVfs["icons/material/dark_mode.xml"]),
  LIGHT_MODE(resourcesVfs["icons/material/light_mode.xml"])
}
