package org.chiachat.app.db

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.chiachat.app.util.Platform
import org.chiachat.app.util.getPlatform
import kotlin.test.Test
import kotlin.test.assertTrue

class DbServiceTest {

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun testVersion() = runTest {
    if (getPlatform() != Platform.ANDROID) {
      val driver = PlatformDb().getDriver()
      val dbService = DbService(driver)
      dbService.runMigrations()
      val version = dbService.getVersion()
      assertTrue { version != null && version > 1L }
      dbService.setVersion(5)
      assertTrue { dbService.getVersion() == 5 }
      if (version != null) { dbService.setVersion(version) }
    }
  }
}
