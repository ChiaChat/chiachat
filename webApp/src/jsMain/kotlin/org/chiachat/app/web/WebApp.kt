package org.chiachat.app.web

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModule
import org.chiachat.app.compose.ComposeRoot
import org.chiachat.app.db.PlatformDb
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class WebApp {

  val composeRoot = mutableStateOf<ComposeRoot?>(null)

  init {
    CoroutineScope(Dispatchers.Default).launch {
      val driver = PlatformDb().getDriver()

      val platformModule = module {
        single { driver }
        factory(named("ioScope")) { CoroutineScope(Dispatchers.Default) }
        factory(named("vmScope")) { CoroutineScope(Dispatchers.Default) }
      }

      startKoin {
        modules(SharedAppModules.sharedModule, ComposeAppModule.composeModule, platformModule)
        allowOverride(false)
      }
      composeRoot.value = ComposeRoot()
    }
  }
  @Composable
  fun View() {
    composeRoot.value?.View()
  }
}
