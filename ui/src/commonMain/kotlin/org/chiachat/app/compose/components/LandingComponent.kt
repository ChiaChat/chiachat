package org.chiachat.app.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.chiachat.app.compose.composables.ProfileCard
import org.koin.core.component.KoinComponent

class LandingComponent(val vm: LandingViewModel = LandingViewModel()) : Component, KoinComponent {

  @Composable
  override fun view() {
    val dbVersion by vm.dbVersion.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Spacer(modifier = Modifier.weight(1f))
          dbVersion?.let {
            Text(text = "Db Version: $it", color = MaterialTheme.colorScheme.onBackground)
          }
          screen()
          Spacer(modifier = Modifier.weight(1f))
        }
  }

  @Composable
  fun screen() {
    val image by vm.image.collectAsState()
    if (image == null) {
      Text("Loading Image")
    } else {
      ProfileCard(demoUser, image)
    }
  }
}
