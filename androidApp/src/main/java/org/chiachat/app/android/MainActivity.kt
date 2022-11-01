package org.chiachat.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import com.soywiz.korio.android.withAndroidContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.SharedAppModules
import org.chiachat.app.compose.ComposeApp
import org.chiachat.app.compose.ComposeAppModules
import org.chiachat.app.compose.services.NavigationService
import org.chiachat.app.compose.theme.ThemeResources
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity(), KoinComponent {
  val mainActivity = this
  val androidModule = module { single { mainActivity } }
  val app = ComposeApp(loadResources = ::loadThemeResources)

  val navigationService: NavigationService by inject()

  init {
    startKoin {
      modules(SharedAppModules.sharedModule, ComposeAppModules.composeModule, androidModule)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContent { app.View() }

    val callback = onBackPressedDispatcher.addCallback(this) { navigationService.back() }

    callback.isEnabled = true
  }

  fun loadThemeResources(resources: MutableStateFlow<ThemeResources>) {
    CoroutineScope(Dispatchers.IO).launch {
      withAndroidContext(mainActivity) { resources.value = ThemeResources() }
    }
  }
}
