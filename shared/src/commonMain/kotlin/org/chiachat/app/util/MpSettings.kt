package org.chiachat.app.util

import com.russhwolf.settings.Settings

class MpSettings {
  val settings: Settings = Settings()
  // Booleans

  //  var homeDir: VfsFile
  //    get() = applicationDataVfs[settings.getStringOrNull(HOME_DIR.name) ?: "."]
  //    set(value) = settings.putString(HOME_DIR.name, value.absolutePath)
}

enum class SettingKeys {
  HOME_DIR,
}
