package org.chiachat.app.db

//import app.cash.sqldelight.db.SqlDriver
//import co.touchlab.kermit.Logger
//import org.chiachat.app.ChiaChatDb

/*
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
      1
    }
  }

  fun setVersion(version: Int): Boolean {
    val setVersionSucceeded =
        try {
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
    return setVersionSucceeded
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
*/
