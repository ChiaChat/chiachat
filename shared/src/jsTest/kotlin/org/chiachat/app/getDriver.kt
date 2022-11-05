package org.chiachat.app

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqljs.initSqlDriver
import kotlinx.coroutines.await

actual suspend fun getDriver(): SqlDriver {
  return initSqlDriver(ChiaChatDb.Schema).await()
}
