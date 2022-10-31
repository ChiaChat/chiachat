package org.chiachat.app.compose.shared

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ButtonDefaults.filledTonalButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val BUTTON_CURVE_PERCENT = 50

@Composable
fun RoundedPrimaryButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
  OutlinedButton(
      modifier = modifier.height(54.dp),
      onClick = onClick,
      shape = RoundedCornerShape(BUTTON_CURVE_PERCENT),
      colors = buttonColors()
      // or shape = CircleShape
      ) {
    Text(text = text, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onPrimary)
  }
}

@Composable
fun PrimaryTextButton(text: String, modifier: Modifier, onClick: () -> Unit) {
  TextButton(modifier = modifier, onClick = onClick) {
    Text(text = text, style = MaterialTheme.typography.labelSmall)
  }
}
