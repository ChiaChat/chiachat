package org.chiachat.app.compose.theme

import android.content.Context
import androidx.compose.ui.text.font.FontFamily
import com.soywiz.korio.android.withAndroidContext
import com.soywiz.korio.file.VfsFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject

val mainActivity: Context by inject()

actual fun loadFontFamily(files: List<VfsFile>, result: MutableStateFlow<FontFamily>) {
  CoroutineScope(Dispatchers.IO).launch {
    withAndroidContext(mainActivity) {
      val fontFamily = FontFamily(files.map { loadFont(it) })
      result.value = fontFamily
    }
  }
}
