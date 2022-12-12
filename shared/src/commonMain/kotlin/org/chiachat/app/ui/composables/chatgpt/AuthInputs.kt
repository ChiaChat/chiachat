package org.chiachat.app.ui.composables.chatgpt

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chiachat.app.ui.composables.Graphics.Graphic
import org.chiachat.app.ui.services.ResourceService
import org.chiachat.app.ui.theme.CchGraphics


object AuthInputs {
    private val textFieldModifier = Modifier.height(60.dp).width(300.dp)
    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 8
    }


    @Composable
    fun Logo() {
        Graphic(CchGraphics.CHIACHAT_LOGO, "ChiaChat Logo", modifier = Modifier.width(300.dp))
    }

    @Composable
    fun TextFieldLabel(label: String) {
        Text(label, color = MaterialTheme.colors.onBackground)
    }


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
        val error = !isValidEmail(email) && email.isNotBlank()

        if (error) {
            Text(
                "Invalid email address",
                color = MaterialTheme.colors.error
            )
            Spacer(Modifier.size(5.dp))
        }

        OutlinedTextField(
            label = { TextFieldLabel("Email") },
            value = email,
            onValueChange = onEmailChange,
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            ),
            isError = error
        )

    }

    @Composable
    fun Password(password: String, onPasswordChange: (String) -> Unit) {
        val error = !isPasswordValid(password) && password.isNotBlank()
        if (error) {
            Text(
                "Password must be at least 8 characters",
                color = MaterialTheme.colors.error
            )
            Spacer(Modifier.size(5.dp))
        }
        OutlinedTextField(
            label = { TextFieldLabel("Password") },
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            ),
            isError = error
        )
    }

    @Composable
    fun ConfirmPassword(password: String, confirmPassword: String, onConfirmPasswordChange: (String) -> Unit) {
        val error = password.isNotBlank() && confirmPassword.isNotBlank() && password != confirmPassword
        if (error) {
            Text(
                "Passwords do not match",
                color = MaterialTheme.colors.error
            )
            Spacer(modifier = Modifier.size(5.dp))
        }
        OutlinedTextField(
            label = { TextFieldLabel("Confirm Password") },
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            isError = error,
            modifier = textFieldModifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            )
        )
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
