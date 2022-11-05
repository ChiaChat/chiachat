package org.chiachat.app.compose

import org.chiachat.app.compose.components.LandingComponent
import org.chiachat.app.compose.services.NavigationService
import org.koin.dsl.module

object ComposeAppModule {
  val composeModule = module { single { NavigationService(LandingComponent()) } }
}