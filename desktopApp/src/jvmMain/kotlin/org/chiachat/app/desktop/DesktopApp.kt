package org.chiachat.app.desktop

import app.cash.sqldelight.db.SqlDriver
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModules
import org.chiachat.app.compose.ComposeRoot
import org.koin.core.context.startKoin

class DesktopApp(driver: SqlDriver) {
  val composeRoot = ComposeRoot()

  init {
    startKoin {
      val desktopModules = DesktopModules()
      val composeModules = ComposeAppModules()
      val sharedModules = SharedAppModules(driver)
      modules(desktopModules.all + composeModules.all + sharedModules.all)
      allowOverride(false)
    }
  }
}
