package org.chiachat.app.compose

import org.chiachat.app.compose.components.LandingComponent
import org.chiachat.app.compose.components.LandingViewModel
import org.chiachat.app.compose.services.NavigationService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class ComposeAppModules {
  val components = module {
    singleOf(::LandingViewModel)
    singleOf(::LandingComponent)
  }


  val services = module { single { NavigationService(get<LandingComponent>()) } }

  val all = services + components
}
