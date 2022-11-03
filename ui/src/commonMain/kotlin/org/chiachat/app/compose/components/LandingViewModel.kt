package org.chiachat.app.compose.components

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.user.UserProfile
import org.jetbrains.skia.Image.Companion.makeFromEncoded

val demoUser = UserProfile("Andrea Bueide", "0x1234567890123456", resourcesVfs["previews/dazai-pfp.png"])
class LandingViewModel : ViewModel() {
    val image: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)

    init {
        ioScope.launch {
            image.value = makeFromEncoded(demoUser.profileImage.readAll()).toComposeImageBitmap()
        }
    }
}
