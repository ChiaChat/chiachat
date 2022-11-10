package org.chiachat.app.android

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import kotlinx.coroutines.runBlocking
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModules
import org.chiachat.app.compose.ComposeRoot
import org.chiachat.app.compose.services.NavigationService
import org.chiachat.app.db.PlatformDb
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity(), KoinComponent {
  val context: Context = this

  val app = ComposeRoot()

  init {
    runBlocking {
      val androidModules = AndroidModules(context)
      startKoin { modules(androidModules.all) }
      val driver = PlatformDb().getDriver()
      val sharedModules = SharedAppModules(driver)
      val composeModules = ComposeAppModules()
      stopKoin()

      startKoin {
        modules(androidModules.all + composeModules.all + sharedModules.all)
        allowOverride(false)
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContent { app.View() }

    val navigationService: NavigationService by inject()
    val callback = onBackPressedDispatcher.addCallback(this) { navigationService.back() }

    callback.isEnabled = true
  }
}
