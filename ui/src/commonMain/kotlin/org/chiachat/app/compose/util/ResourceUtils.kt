package org.chiachat.app.compose.util

import androidx.compose.ui.graphics.ImageBitmap
import com.soywiz.korio.file.VfsFile

expect suspend fun VfsFile.readImageBitmap(): ImageBitmap
