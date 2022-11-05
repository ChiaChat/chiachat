package org.chiachat.app.web

import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModule
import org.chiachat.app.compose.ComposeRoot
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class WebApp {

  val mainComponent = ComposeRoot()

  val platformModule = module {
    factory(named("ioScope")) { CoroutineScope(Dispatchers.Default) }
    factory(named("vmScope")) { CoroutineScope(Dispatchers.Default) }
  }

  init {
    startKoin {
      modules(SharedAppModules.sharedModule, ComposeAppModule.composeModule, platformModule)
      allowOverride(false)
    }
  }
  @Composable
  fun View() {
    mainComponent.View()
  }
}
