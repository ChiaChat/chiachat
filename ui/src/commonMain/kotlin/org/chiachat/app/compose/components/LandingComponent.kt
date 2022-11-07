package org.chiachat.app.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.chiachat.app.compose.composables.ProfileCard

interface ILandingComponent : Component {
  override val vm: ILandingViewModel
}

class LandingComponent(override val vm: LandingViewModel) : ILandingComponent {
  @Composable
  override fun View() {
    val dbVersion by vm.dbVersion.collectAsState()
    val text by vm.textField.collectAsState()
    val users by vm.users.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Spacer(modifier = Modifier.weight(1f))
          dbVersion?.let {
            Text(text = "Db Version: $it", color = MaterialTheme.colorScheme.onBackground)
          }
          TextField(text, onValueChange = { vm.textField.value = it })
          Button(onClick = vm::addProfile) { Text("Add Profile") }
          LazyColumn { users.forEach { item(it) { ProfileCard(it) } } }
          Spacer(modifier = Modifier.weight(1f))
        }
  }
}
