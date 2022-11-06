package org.chiachat.app.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.soywiz.korio.file.std.userHomeVfs

actual class PlatformDb {
  val sqlitePath = userHomeVfs[".chiachat/chiachat.db"]
  actual suspend fun getDriver(): SqlDriver {
    sqlitePath.parent.mkdirs()
    val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${sqlitePath.absolutePath}")
    return driver
  }
}
