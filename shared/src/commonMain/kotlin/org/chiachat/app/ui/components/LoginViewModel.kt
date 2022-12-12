package org.chiachat.app.ui.components

import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.app.ui.services.ResourceService
import org.chiachat.app.ui.services.ThemeService
import org.koin.core.component.inject

internal interface ILoginViewModel : IViewModel {
  val themeService: ThemeService
  val resourceService: ResourceService

  val server: MutableStateFlow<String>
  val username: MutableStateFlow<String>
  val password: MutableStateFlow<String>

  fun onLogin()
}

internal class LoginViewModel : ViewModel(), ILoginViewModel {
  override val themeService: ThemeService by inject()
  override val resourceService: ResourceService by inject()

  override val server: MutableStateFlow<String> = MutableStateFlow("matrix.natnat.xyz")
  override val username: MutableStateFlow<String> = MutableStateFlow("andrea")
  override val password: MutableStateFlow<String> = MutableStateFlow("")

  override fun onLogin() {
    Logger.i { "Login tapped" }
  }
}
