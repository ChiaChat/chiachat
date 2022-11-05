package org.chiachat.app.compose.components

import androidx.compose.ui.graphics.ImageBitmap
import co.touchlab.kermit.Logger
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.compose.util.readImageBitmap
import org.chiachat.app.db.DbService
import org.chiachat.app.user.UserProfile
import org.koin.core.component.inject

val demoUser =
    UserProfile("Andrea Bueide", "0x1234567890123456", resourcesVfs["previews/dazaipfp.png"])

class LandingViewModel : ViewModel() {
  val dbService: DbService by inject()
  val image: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)
  val dbVersion = MutableStateFlow<Int?>(null)

  init {
    ioScope.launch {
      resourcesVfs["."].listRecursive().collect { Logger.i(it.path) }
      image.value = demoUser.profileImage.readImageBitmap()
      dbVersion.value = dbService.db.versionTableQueries.getVersion().executeAsOneOrNull()?.toInt()
    }
  }
}
