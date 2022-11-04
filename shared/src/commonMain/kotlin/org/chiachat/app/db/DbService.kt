package org.chiachat.app.db

import app.cash.sqldelight.db.SqlDriver
import org.chiachat.app.ChiaChatDb

class DbService(val db: ChiaChatDb, val driver: SqlDriver) {
  init {
    runMigrations()
  }

  fun runMigrations() {
    val currentVersion =
        try {
          db.versionTableQueries.getVersion().executeAsList().first()
        } catch (e: Exception) {
          1
        }
    val latestVersion = ChiaChatDb.Schema.version
    ChiaChatDb.Schema.migrate(driver, currentVersion.toInt(), latestVersion)
    db.versionTableQueries.setVersion(latestVersion.toLong())
  }
}
