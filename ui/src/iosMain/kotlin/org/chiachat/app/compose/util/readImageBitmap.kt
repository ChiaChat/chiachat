package org.chiachat.app.compose.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.soywiz.korio.file.VfsFile
import org.jetbrains.skia.Image.Companion.makeFromEncoded

actual suspend fun VfsFile.readImageBitmap(): ImageBitmap {
    return makeFromEncoded(readAll()).toComposeImageBitmap()
}