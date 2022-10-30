package org.chiachat.app.compose.shared

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val BUTTON_CURVE_PERCENT = 50

@Composable
fun RoundedPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier.height(54.dp),
        onClick = onClick,
        shape = RoundedCornerShape(BUTTON_CURVE_PERCENT),
        // or shape = CircleShape
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun PrimaryTextButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}
