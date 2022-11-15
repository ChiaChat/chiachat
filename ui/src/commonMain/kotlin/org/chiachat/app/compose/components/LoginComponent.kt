package org.chiachat.app.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import org.chiachat.app.compose.composables.CchTextField

class LoginComponent: Component {

    override val vm: ILoginViewModel = LoginViewModel()

    @Composable
    override fun View() {
        Column(){
            CchTextField(vm.username)
        }
    }
}