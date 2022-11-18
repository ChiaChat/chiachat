package org.chiachat.app.compose.util

import androidx.compose.ui.graphics.ImageBitmap
import com.soywiz.korio.file.VfsFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

expect suspend fun VfsFile.readImageBitmap(): ImageBitmap

fun VfsFile.loadImageBitmap(scope: CoroutineScope, onLoad: (ImageBitmap) -> Unit){

}
