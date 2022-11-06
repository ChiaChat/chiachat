package org.chiachat.app

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.chiachat.app.db.DbService
import org.chiachat.app.db.PlatformDb
import org.chiachat.app.util.Platform
import org.chiachat.app.util.getPlatform
import kotlin.test.Test
import kotlin.test.assertTrue

class DbTest {

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun testVersion() = runTest {
    if (getPlatform() != Platform.ANDROID) {
      val driver = PlatformDb().getDriver()
      val dbService = DbService(driver)
      val version = dbService.db.versionTableQueries.getVersion().executeAsOneOrNull()
      assertTrue { version != null && version > 1L }
    }
  }
}
