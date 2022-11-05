package org.chiachat.app.db

import app.cash.sqldelight.db.SqlDriver

expect class PlatformDb() {
  suspend fun getDriver(): SqlDriver
}
