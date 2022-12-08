package org.chiachat.app.compose

import org.chiachat.app.compose.components.LandingComponent
import org.chiachat.app.compose.components.LandingViewModel
import org.chiachat.app.compose.components.LoginComponent
import org.chiachat.app.compose.components.LoginViewModel
import org.chiachat.app.compose.services.NavigationService
import org.chiachat.app.compose.services.ResourceService
import org.chiachat.app.compose.services.ThemeService
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class ComposeAppModules {
  val components = module {
    singleOf(::LandingViewModel)
    singleOf(::LandingComponent)
    singleOf(::LoginComponent)
    singleOf(::LoginViewModel)
  }

  val services = module {
    single { NavigationService(get<LoginComponent>()) }
    single { ResourceService(get(named("ioScope"))) }
    singleOf(::ThemeService)
  }

  val all = services + components
}
