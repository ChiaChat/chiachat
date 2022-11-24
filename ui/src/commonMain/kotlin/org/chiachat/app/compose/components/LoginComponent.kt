package org.chiachat.app.compose.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.chiachat.app.compose.composables.CchActionButton
import org.chiachat.app.compose.composables.CchTextField
import org.chiachat.app.compose.composables.ToggleDarkModeButton

class LoginComponent : Component {

    override val vm: ILoginViewModel = LoginViewModel()

    @Composable
    override fun View() {

        Box(modifier = Modifier.padding(40.dp).fillMaxSize()) {
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
        }
    }
}
