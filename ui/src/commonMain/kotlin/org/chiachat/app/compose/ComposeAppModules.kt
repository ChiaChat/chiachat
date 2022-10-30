package org.chiachat.app.compose

import org.chiachat.app.compose.components.LandingComponent
import org.chiachat.app.compose.navigation.NavigationService
import org.koin.dsl.module

object ComposeAppModules {

  val composeModule = module {
    single { NavigationService(LandingComponent()) }
  }

}
