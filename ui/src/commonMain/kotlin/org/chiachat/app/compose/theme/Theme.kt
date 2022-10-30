package org.chiachat.app.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable



@Composable
fun ChiaChatTheme(
    resources: ChiaChatResources,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = if(isSystemInDarkTheme()) chiachatDarkScheme else chiachatDarkScheme,
//        typography = ChiaChatT,
        content = content
    )
}
