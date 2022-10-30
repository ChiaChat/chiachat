package org.chiachat.app

import LandingComponent
import co.touchlab.kermit.Logger
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chiachat.app.compose.navigation.NavigationService
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ComposeApp {
  val navigationService = NavigationService().also { it.navigate(LandingComponent()) }

  val platformModule = module { single { navigationService } }

  init {
    startKoin { modules(MnpApp.appModule, platformModule) }
    CoroutineScope(Dispatchers.Default).launch {
      Logger.i { "ui"}
      resourcesVfs["."].list().collect { Logger.i(it.path) }
      Logger.i {resourcesVfs["MR/base/colors.xml"].exists().toString() }
      Logger.i {resourcesVfs["fonts/OFL.txt"].exists().toString() }
    }
  }
}
