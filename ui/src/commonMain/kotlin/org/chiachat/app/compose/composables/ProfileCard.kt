@file:OptIn(ExperimentalMaterial3Api::class)

package org.chiachat.app.compose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import org.chiachat.app.user.UserProfile
import org.jetbrains.skia.Image.Companion.makeFromEncoded

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard(profile: UserProfile, image: ImageBitmap?) {
  Card(
      shape = RoundedCornerShape(8.dp),
      colors =
          CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
      modifier =
          Modifier.padding(horizontal = 40.dp)
              .padding(vertical = 10.dp)
              .fillMaxWidth(0.75f)
              .fillMaxHeight(0.1f),
  ) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {
          if (image != null) {
//            val image = makeFromEncoded(imageData).toComposeImageBitmap()
            Image(
                painter = BitmapPainter(image),
                contentDescription = "Sample",
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Fit)
          }
          Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(profile.username)
            Text(profile.fingerprint)
          }
        }
  }
}
