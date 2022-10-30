package org.chiachat.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import org.chiachat.app.ComposeApp
import org.chiachat.app.compose.components.shared.main.MainComponent
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {
    val app = ComposeApp()
    val mainComponent = MainComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainComponent.view()
        }

        val callback = onBackPressedDispatcher.addCallback(this) {
            app.navigationService.back()
        }

        callback.isEnabled = true
    }
}
