package org.chiachat.app.compose.components

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.app.compose.services.ResourceService
import org.chiachat.app.compose.services.ThemeService
import org.chiachat.app.compose.theme.CchIcons
import org.koin.core.component.inject

interface ILoginViewModel : IViewModel {
  val themeService: ThemeService
  val resourceService: ResourceService

  val server: MutableStateFlow<String>
  val username: MutableStateFlow<String>
  val password: MutableStateFlow<String>

  val darkModeIcon: MutableStateFlow<ImageBitmap?>
  val lightModeIcon: MutableStateFlow<ImageBitmap?>

  fun onLogin()
}

class LoginViewModel : ViewModel(), ILoginViewModel {
  override val themeService: ThemeService by inject()
  override val resourceService: ResourceService by inject()


  override val server: MutableStateFlow<String> = MutableStateFlow("matrix.natnat.xyz")
  override val username: MutableStateFlow<String> = MutableStateFlow("andrea")
  override val password: MutableStateFlow<String> = MutableStateFlow("")


  override val darkModeIcon: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)
  override val lightModeIcon: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)

  init {
    resourceService.loadIcon(CchIcons.DARK_MODE){ darkModeIcon.value = it }
    resourceService.loadIcon(CchIcons.LIGHT_MODE){ lightModeIcon.value = it }
  }

  override fun onLogin() {}
}
