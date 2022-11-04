package org.chiachat.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import com.soywiz.korio.android.AndroidCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeAppModule
import org.chiachat.app.compose.ComposeRoot
import org.chiachat.app.compose.services.NavigationService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MainActivity : ComponentActivity(), KoinComponent {
  val ioContext = Dispatchers.IO + AndroidCoroutineContext(this)
  val vmContext = Dispatchers.Default + AndroidCoroutineContext(this)

  val androidModule = module {
    factory(named("ioScope")) { CoroutineScope(ioContext) }
    factory(named("vmScope")) { CoroutineScope(vmContext) }
  }
  val app = ComposeRoot()

  val navigationService: NavigationService by inject()

  init {
    startKoin {
      modules(SharedAppModules.sharedModule, ComposeAppModule.composeModule, androidModule)
      allowOverride(false)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContent { app.View() }

    val callback = onBackPressedDispatcher.addCallback(this) { navigationService.back() }

    callback.isEnabled = true
  }
}
