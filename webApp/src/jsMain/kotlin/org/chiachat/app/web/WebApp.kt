package org.chiachat.app.web

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModules
import org.chiachat.app.compose.ComposeRoot
import org.chiachat.app.db.PlatformDb
import org.koin.core.context.startKoin

class WebApp {

  val composeRoot = mutableStateOf<ComposeRoot?>(null)

  init {
    CoroutineScope(Dispatchers.Default).launch {
      val driver = PlatformDb().getDriver()

      val webModules = WebModules()
      val composeModules = ComposeAppModules()
      val sharedModules = SharedAppModules(driver)

      startKoin {
        modules(webModules.all + composeModules.all + sharedModules.all)
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
