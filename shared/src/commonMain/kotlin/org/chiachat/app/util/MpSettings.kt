package org.chiachat.app.util

import com.russhwolf.settings.Settings
import org.chiachat.app.util.SettingKeys.REFRESH_TOKEN

class MpSettings {
  val settings: Settings = Settings()

  //  var homeDir: VfsFile
  //    get() = applicationDataVfs[settings.getStringOrNull(HOME_DIR.name) ?: "."]
  //    set(value) = settings.putString(HOME_DIR.name, value.absolutePath)
  var refreshToken: String
    get() = settings.getString(REFRESH_TOKEN.name, "")
    set(value) = settings.putString(REFRESH_TOKEN.name, value)
}

enum class SettingKeys {
  REFRESH_TOKEN,
}
