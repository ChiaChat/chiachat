package org.chiachat.app.db

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import org.chiachat.app.ChiaChatDb

const val SQLITE_EXCEPTION_NAME = "SQLiteException"

class DbService(val driver: SqlDriver) {

  var db: ChiaChatDb = ChiaChatDb.invoke(driver)

  init {
    runMigrations()
  }

  @Suppress("TooGenericExceptionCaught")
  fun runMigrations() {
    val currentVersion =
        try {
          db.versionTableQueries.getVersion().executeAsList().first()
        } catch (e: Exception) {
          if (e::class.simpleName != SQLITE_EXCEPTION_NAME) {
            Logger.e { e.stackTraceToString() }
          }
          1
        }
    val latestVersion = ChiaChatDb.Schema.version
    ChiaChatDb.Schema.migrate(driver, currentVersion.toInt(), latestVersion)
    db.versionTableQueries.setVersion(latestVersion.toLong())
  }
}
