package org.chiachat.app.db

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import org.chiachat.app.ChiaChatDb
import org.chiachat.app.util.Platform
import org.chiachat.app.util.getPlatform

const val SQLITE_EXCEPTION_NAME = "SQLiteException"
const val VERSION_TABLE_EXISTS = "table VersionTable already exists"

class DbService(val driver: SqlDriver) {

  var db: ChiaChatDb = ChiaChatDb.invoke(driver)

  init {
      runMigrations()
  }

  @Suppress("TooGenericExceptionCaught")
  fun getVersion(): Int? {
    return try {
      db.versionTableQueries.getVersion().executeAsOneOrNull()?.toInt()
    } catch (e: Exception) {
      Logger.e { e.stackTraceToString() }
      1
    }
  }

  fun setVersion(version: Int): Boolean {
    return try {
      val tableVersion = db.versionTableQueries.getVersion().executeAsOneOrNull()
      if (tableVersion == null) {
        db.versionTableQueries.setVersion(version.toLong())
      } else {
        db.versionTableQueries.updateVersion(version.toLong())
      }
      true
    } catch (e: Exception) {
      Logger.e { e.stackTraceToString() }
      false
    }
  }

  @Suppress("TooGenericExceptionCaught")
  fun runMigrations() {
    val currentVersion = getVersion()
    val latestVersion = ChiaChatDb.Schema.version
    if (currentVersion == null) {
      // if table exists, assume latest version, try to recover
      if (setVersion(latestVersion)) runMigrations() else throw MigrationException()
    } else {
      if (currentVersion != latestVersion) {
        ChiaChatDb.Schema.migrate(driver, currentVersion, latestVersion)
        if (!setVersion(latestVersion)) throw FailedToSetDbVersionException()
      }
    }
  }
}

class FailedToSetDbVersionException : Exception()

class MigrationException : Exception("Critical Error: Failed to run database migration")
