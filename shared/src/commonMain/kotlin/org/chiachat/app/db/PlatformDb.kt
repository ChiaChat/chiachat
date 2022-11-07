package org.chiachat.app.db

import app.cash.sqldelight.db.SqlDriver
import com.soywiz.korio.file.VfsFile

expect class PlatformDb() {
  suspend fun getDriver(): SqlDriver
}
