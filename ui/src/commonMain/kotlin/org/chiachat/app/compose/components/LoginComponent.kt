package org.chiachat.app.compose.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class LoginComponent: Component {

    override val vm: IViewModel = LoginViewModel()

    @Composable
    override fun View() {
        Text("Test")
    }
}