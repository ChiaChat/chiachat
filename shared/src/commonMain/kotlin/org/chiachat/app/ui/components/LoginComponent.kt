package org.chiachat.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.chiachat.app.ui.composables.CchActionButton
import org.chiachat.app.ui.composables.CchTextField
import org.chiachat.app.ui.composables.ToggleDarkModeButton
import org.chiachat.app.ui.composables.chatgpt.LoginComposables
import org.chiachat.app.ui.composables.chatgpt.RegistrationComposables
import org.chiachat.app.ui.services.ResourceService
import org.koin.core.component.inject

internal class LoginComponent : Component {

    override val vm: ILoginViewModel = LoginViewModel()

    val resources: ResourceService by inject()

    @Composable
    override fun View() {
        var register by remember { mutableStateOf(true) }
        if (register) {
            RegistrationComposables.RegistrationScreen(resources) { register = false }
        } else {
            LoginComposables.LoginScreen(resources)
        }
        /*Box(modifier = Modifier.padding(40.dp).fillMaxSize()) {
            ToggleDarkModeButton(vm.themeService, vm.resourceService, Modifier.align(Alignment.TopEnd))
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center).padding(40.dp).width(320.dp)
            ) {
                CchTextField(vm.server, "server", "chiachat.org")
                CchTextField(vm.username, "username", "@username")
                CchTextField(vm.password, "password")
                CchActionButton("Login", onClick = vm::onLogin)
            }
        }*/
    }
}
