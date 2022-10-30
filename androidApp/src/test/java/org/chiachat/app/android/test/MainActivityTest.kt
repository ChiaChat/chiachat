package org.chiachat.app.android.test

import org.chiachat.app.Greeting
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MainActivityTest {
    @Test
    fun greet() {
        assertTrue(Greeting().greeting().contains("Android"))
    }
}
