package org.chiachat.app.ui.composables.chatgpt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chiachat.app.ui.composables.Graphic
import org.chiachat.app.ui.services.ResourceService
import org.chiachat.app.ui.theme.CchGraphics


object AuthInputs {
    @Composable
    fun Logo(resources: ResourceService) {
        Graphic(resources, CchGraphics.CHIACHAT_LOGO, "ChiaChat Logo", modifier = Modifier.width(300.dp))
    }

    @Composable
    fun TextFieldLabel(label: String) {
        Text(label, color = MaterialTheme.colors.onBackground)
    }

    private val textFieldModifier = Modifier.height(60.dp).width(300.dp)

    @Composable
    fun Username(username: String, onUsernameChange: (String) -> Unit) {
        OutlinedTextField(
            label = { TextFieldLabel("Username") },
            value = username,
            onValueChange = onUsernameChange,
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            )
        )
    }

    @Composable
    fun Email(email: String, onEmailChange: (String) -> Unit) {
        OutlinedTextField(
            label = { TextFieldLabel("Email") },
            value = email,
            onValueChange = onEmailChange,
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            )
        )
    }

    @Composable
    fun Password(password: String, onPasswordChange: (String) -> Unit) {
        OutlinedTextField(
            label = { TextFieldLabel("Password") },
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            )
        )
    }

    @Composable
    fun ConfirmPassword(password: String, confirmPassword: String, onConfirmPasswordChange: (String) -> Unit) {
        Column {
            OutlinedTextField(
                label = { TextFieldLabel("Confirm Password") },
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                visualTransformation = PasswordVisualTransformation(),
                modifier = textFieldModifier,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = MaterialTheme.colors.primary,
                )
            )
            if (confirmPassword != password) {
                Text(
                    "Passwords do not match",
                    color = MaterialTheme.colors.error
                )
            }
        }
    }

    @Composable
    fun ActionButton(text: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier.width(300.dp)
        ) {
            Text(text, fontWeight = FontWeight.W700, fontSize = 18.sp, color = MaterialTheme.colors.onPrimary)
        }
    }
}
