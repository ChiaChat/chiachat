package org.chiachat.app.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soywiz.korio.file.std.resourcesVfs
import org.chiachat.app.compose.composables.ProfileCard
import org.chiachat.app.user.UserProfile

class LandingComponent : Component() {
  val demoUser =
      UserProfile("Andrea Bueide", "0x1234567890123456", resourcesVfs["previews/dazai-pfp.png"])
  @Composable
  override fun view() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Spacer(modifier = Modifier.weight(1f))
          screen()
          Spacer(modifier = Modifier.weight(1f))
        }
  }

  @Composable
  fun screen() {
    ProfileCard(demoUser)
  }
}
