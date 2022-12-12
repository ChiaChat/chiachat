package org.chiachat.app.ui.components.register

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.chiachat.app.ui.components.login.AuthInputs
import org.chiachat.app.ui.services.ResourceService

internal object RegistrationComposables {

    @Composable
    fun RegistrationScreen(resources: ResourceService, onRegister: () -> Unit) {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(0.75f).align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthInputs.Logo()
                Spacer(Modifier.height(16.dp))
                AuthInputs.Username(username, onUsernameChange = { username = it })
                Spacer(Modifier.height(16.dp))
                AuthInputs.Email(email, onEmailChange = { email = it })
                Spacer(Modifier.height(16.dp))
                AuthInputs.Password(password, onPasswordChange = { password = it })
                Spacer(Modifier.height(16.dp))
                AuthInputs.ConfirmPassword(
                    password,
                    confirmPassword,
                    onConfirmPasswordChange = { confirmPassword = it })
                Spacer(Modifier.height(32.dp))
                AuthInputs.ActionButton("Create Account", onRegister)
            }
        }
    }
}
