package org.chiachat.app.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import org.chiachat.app.ui.services.ResourceService
import org.chiachat.app.ui.theme.CchGraphics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


internal object Graphics: KoinComponent {

    val resources: ResourceService by inject()

    @Composable
    fun Graphic(
        graphic: CchGraphics,
        contentDescription: String,
        tint: Color = MaterialTheme.colors.primary,
        modifier: Modifier = Modifier
    ) {

        var graphicBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(true) {
            resources.loadGraphic(graphic) {
                graphicBitmap = it
            }
        }

        graphicBitmap?.let {
            Icon(
                bitmap = it,
                contentDescription = contentDescription,
                tint = tint,
                modifier = Modifier.size(64.dp).then(modifier)
            )
        }
    }
}