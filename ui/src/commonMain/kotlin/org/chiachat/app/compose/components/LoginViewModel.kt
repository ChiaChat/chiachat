package org.chiachat.app.compose.components

import kotlinx.coroutines.flow.MutableStateFlow

interface ILoginViewModel : IViewModel {
    val server: MutableStateFlow<String>
    val username: MutableStateFlow<String>
    val password: MutableStateFlow<String>

    fun onLogin()
}

class LoginViewModel: ViewModel(), ILoginViewModel  {
    override val server: MutableStateFlow<String> = MutableStateFlow("")
    override val username: MutableStateFlow<String> = MutableStateFlow("")
    override val password: MutableStateFlow<String> = MutableStateFlow("")

    override fun onLogin() {

    }
}