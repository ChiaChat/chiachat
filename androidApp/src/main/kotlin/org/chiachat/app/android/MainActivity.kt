package org.chiachat.app.android

import AndroidRoot
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.stopKoin

class MainActivity : AppCompatActivity() {
    val root = AndroidRoot(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            root.View()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}
