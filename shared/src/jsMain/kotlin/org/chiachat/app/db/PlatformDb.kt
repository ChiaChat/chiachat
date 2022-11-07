package org.chiachat.app.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqljs.initSqlDriver
import com.soywiz.korio.file.VfsFile
import kotlinx.coroutines.await
import org.chiachat.app.ChiaChatDb

actual class PlatformDb {
  actual suspend fun getDriver(): SqlDriver {
    return initSqlDriver(ChiaChatDb.Schema).await()
  }
}
