package org.chiachat.app.compose

import org.chiachat.app.compose.components.ILandingViewModel
import org.chiachat.app.compose.components.LandingComponent
import org.chiachat.app.compose.components.LandingViewModel
import org.chiachat.app.compose.services.NavigationService
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ComposeAppModules {
  val composeModule = module {
    singleOf(::LandingViewModel)
    singleOf(::LandingComponent)
    single { NavigationService(get<LandingComponent>()) }
  }
}
