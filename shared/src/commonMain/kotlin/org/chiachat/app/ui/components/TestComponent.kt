package org.chiachat.app.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class TestComponent: Component {
    override val vm: IViewModel = TestVm()

    @Composable
    override fun View() {
        Text("Test Text", color = Color.Green)
    }
}

internal class TestVm: IViewModel, KoinComponent {
    override val vmScope: CoroutineScope by inject()
    override val ioScope: CoroutineScope by inject()
}