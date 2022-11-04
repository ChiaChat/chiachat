package org.chiachat.app.compose.components

import androidx.compose.ui.graphics.ImageBitmap
import co.touchlab.kermit.Logger
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.compose.util.readImageBitmap
import org.chiachat.app.user.UserProfile

val demoUser =
    UserProfile("Andrea Bueide", "0x1234567890123456", resourcesVfs["previews/dazaipfp.png"])

class LandingViewModel : ViewModel() {
  val image: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)

  init {
    ioScope.launch {
      resourcesVfs["."].listRecursive().collect { Logger.i(it.path) }
      image.value = demoUser.profileImage.readImageBitmap()
    }
  }
}
