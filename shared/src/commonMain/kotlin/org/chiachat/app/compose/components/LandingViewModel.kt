package org.chiachat.app.compose.components

import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.chiachat.app.compose.composables.ProfileCardItem
import org.chiachat.app.compose.util.readImageBitmap
import org.chiachat.app.db.DbService
import org.chiachat.app.user.UserProfile

const val defaultPath = "previews/dazaipfp.png"

interface ILandingViewModel : IViewModel {
  val dbVersion: MutableStateFlow<Int?>
  val users: MutableStateFlow<List<ProfileCardItem>>
  val textField: MutableStateFlow<String>

  fun addProfile() {
    ioScope.launch {
      val image = resourcesVfs[defaultPath]
      val name = textField.value
      users.value += ProfileCardItem(name, image.readImageBitmap())
      insertProfile(UserProfile(name, image))
    }
  }
  fun setDbVersion()
  fun refreshProfiles()
  fun insertProfile(profile: UserProfile)
}

class LandingViewModel(val dbService: DbService) : ViewModel(), ILandingViewModel {
  override val dbVersion = MutableStateFlow<Int?>(null)
  override val users = MutableStateFlow(emptyList<ProfileCardItem>())
  override val textField = MutableStateFlow("")

  init {
    ioScope.launch {
      setDbVersion()
      refreshProfiles()
    }
  }

  override fun setDbVersion() {
    dbVersion.value = dbService.getVersion()
  }

  override fun refreshProfiles() {
    dbService.db.profileTableQueries.getProfiles().executeAsList().forEach { user ->
      ioScope.launch {
        val image = resourcesVfs[user.profile_image ?: defaultPath].readImageBitmap()
        users.value += ProfileCardItem(user.name, image)
      }
    }
  }

  override fun insertProfile(profile: UserProfile) {
    dbService.db.profileTableQueries.addProfile(profile.username, profile.profileImage.path)
  }
}
