package org.chiachat.app.util

import kotlin.test.Test
import kotlin.test.assertEquals

class SettingsTest {

  val testString = "Test String"
  @Test
  fun CheckSettingsAreSet() {
    if (getPlatform() != Platform.ANDROID) {
      val settings = MpSettings()
      val refresh = settings.refreshToken
      settings.refreshToken = testString
      assertEquals(testString, settings.refreshToken)
      // Set state back to original
      settings.refreshToken = refresh
      assertEquals(refresh, settings.refreshToken)
    }
  }
}
