package org.chiachat.app.compose.navigation

import org.chiachat.app.compose.components.LandingComponent
import org.chiachat.app.compose.components.auth.login.LoginComponent
import org.chiachat.app.compose.components.auth.register.RegisterComponent
import kotlin.test.Test
import kotlin.test.assertTrue

class NavigationServiceTest {

    @Test
    fun navTest() {
        val nav = NavigationService()
        // test navigation
        nav.navigate(LandingComponent())
        assertTrue(nav.currentScreen.value is LandingComponent)
        nav.navigate(LoginComponent())
        assertTrue(nav.currentScreen.value is LoginComponent)
        nav.navigate(RegisterComponent())
        // test back
        assertTrue(nav.currentScreen.value is RegisterComponent)
        nav.back()
        assertTrue(nav.currentScreen.value is LoginComponent)
        nav.back()

        // test forward
        assertTrue(nav.currentScreen.value is LandingComponent)
        nav.forward()
        assertTrue(nav.currentScreen.value is LoginComponent)
        nav.forward()
        assertTrue(nav.currentScreen.value is RegisterComponent)

        // test back/forward/back/forward
        nav.back()
        assertTrue(nav.currentScreen.value is LoginComponent)
        nav.forward()
        assertTrue(nav.currentScreen.value is RegisterComponent)
        nav.back()
        assertTrue(nav.currentScreen.value is LoginComponent)
        nav.forward()
        assertTrue(nav.currentScreen.value is RegisterComponent)
    }

    @Test
    fun twoScreenTest() {
        val nav = NavigationService()
        // test navigation
        nav.navigate(LandingComponent())
        assertTrue(nav.currentScreen.value is LandingComponent)
        nav.navigate(LoginComponent())
        assertTrue(nav.currentScreen.value is LoginComponent)
        nav.back()
        assertTrue(nav.currentScreen.value is LandingComponent)
        nav.navigate(LoginComponent())
        assertTrue(nav.currentScreen.value is LoginComponent)
        nav.back()
        assertTrue(nav.currentScreen.value is LandingComponent)
    }
}
