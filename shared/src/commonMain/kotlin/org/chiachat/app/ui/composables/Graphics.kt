package org.chiachat.app.ui.composables

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import org.chiachat.app.ui.services.ResourceService
import org.chiachat.app.ui.theme.CchGraphics


@Composable
fun Graphic(
    resources: ResourceService,
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
            modifier = modifier
        )
    }
}