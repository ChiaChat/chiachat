package org.chiachat.app.ui

import org.chiachat.app.ui.components.login.LoginComponent
import org.chiachat.app.ui.components.login.LoginViewModel
import org.chiachat.app.ui.services.NavigationService
import org.chiachat.app.ui.services.ResourceService
import org.chiachat.app.ui.services.ThemeService
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class ComposeAppModules {
  val components = module {
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
