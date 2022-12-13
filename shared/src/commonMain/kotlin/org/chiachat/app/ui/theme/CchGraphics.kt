package org.chiachat.app.ui.theme

import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs

enum class CchGraphics(val file: VfsFile) {
  DARK_MODE(resourcesVfs["assets/icons/material/dark_mode.png"]),
  LIGHT_MODE(resourcesVfs["assets/icons/material/light_mode.png"]),
  CHIACHAT_LOGO(resourcesVfs["assets/icons/chiachat/chiachat-logo.png"]),
  CHIACHAT_ICON(resourcesVfs["assets/icons/chiachat/chiachat-icon.png"]),
  NOTIFICATION_BELL(resourcesVfs["assets/icons/material/notification_bell.png"]),
  MENU(resourcesVfs["assets/icons/material/menu.png"]),
}
