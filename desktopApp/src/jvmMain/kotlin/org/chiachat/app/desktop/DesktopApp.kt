package org.chiachat.app.desktop

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModules
import org.chiachat.app.compose.ComposeRoot
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DesktopApp {
  val composeRoot = ComposeRoot()

  val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

  val desktopModule = module { single { driver } }

  init {
    startKoin {
      modules(
          desktopModule,
          SharedAppModules.sharedModule,
          ComposeAppModules.composeModule,
      )
    }
  }
}
