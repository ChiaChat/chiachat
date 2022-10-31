package org.chiachat.app.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.chiachat.app.compose.shared.PrimaryTextButton
import org.chiachat.app.compose.shared.RoundedPrimaryButton

class LandingComponent : Component() {
  @Composable
  override fun view() {
    LandingScreen()
  }

  @Composable
  fun LandingScreen() {
    Box(
        Modifier.fillMaxSize()
            .padding(horizontal = 40.dp)
            .padding(top = 120.dp)
            .padding(bottom = 40.dp)
        ,
    ) {
      Column {
        title()
        description()
      }
      RoundedPrimaryButton(
          text = "Register",
          modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
          onClick = {})
      PrimaryTextButton(
          text = "Login", modifier = Modifier.align(Alignment.BottomCenter), onClick = {})
    }
  }

  companion object {
    @Composable
    private fun title() {
      Text(text = "ChiaChat", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
    }

    @Composable
    private fun description() {
      Text(
          text = "Free & Open Source Matrix Client",
          style = MaterialTheme.typography.labelLarge,
          color = MaterialTheme.colorScheme.secondary
          )
    }
  }
}
