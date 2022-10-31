package org.chiachat.app.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.compose.navigation.NavigationService
import org.chiachat.app.compose.theme.ChiaChatTheme
import org.chiachat.app.compose.theme.ThemeResources
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

fun loadResourcesDefault(resources: MutableStateFlow<ThemeResources>) {
  CoroutineScope(Dispatchers.Default).launch { resources.value = ThemeResources() }
}

class ComposeApp(
    loadResources: (MutableStateFlow<ThemeResources>) -> Unit = ::loadResourcesDefault
) : KoinComponent {
  val navigationService: NavigationService by inject()

  val themeResources = MutableStateFlow(ThemeResources())

  init {
    loadResources(themeResources)
  }

  @Composable
  fun View() {
    val res by themeResources.collectAsState()
    ChiaChatTheme() {
      Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        navigationService.currentView()
      }
    }
  }
}
