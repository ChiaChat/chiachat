package org.chiachat.app.compose.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.app.compose.services.ResourceService
import org.chiachat.app.compose.services.ThemeService
import org.chiachat.app.compose.theme.CchIcons

@Composable
fun CchTextField(
    textProperty: MutableStateFlow<String>,
    label: String? = null,
    placeholder: String? = label,
    modifier: Modifier = Modifier
) {
    val text: String by textProperty.collectAsState()
    OutlinedTextField(
        value = text,
        label = @Composable { label?.let { Text(it) } },
        placeholder = @Composable { placeholder?.let { Text(it) } },
        onValueChange = { newText: String -> textProperty.value = newText },
        modifier = Modifier.fillMaxWidth().then(modifier)
    )
}

@Composable
fun CchActionButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick,
        colors =
        ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colors.tertiary,
            contentColor = MaterialTheme.colors.onTertiary
        ),
        modifier = Modifier.fillMaxWidth().then(modifier)
    ) {
        Text(text)
    }
}

@Composable
fun ToggleDarkModeButton(
    themeService: ThemeService,
    resourceService: ResourceService,
    modifier: Modifier = Modifier
) {
    var darkIcon by remember { mutableStateOf<ImageBitmap?>(null) }
    var lightIcon by remember { mutableStateOf<ImageBitmap?>(null) }
    val isDarkMode by themeService.darkMode.collectAsState()

    LaunchedEffect(true) {
        resourceService.loadIcon(CchIcons.DARK_MODE) {
            darkIcon = it
        }
        resourceService.loadIcon(CchIcons.LIGHT_MODE) {
            lightIcon = it
        }
    }

    IconButton(onClick = themeService::toggleDarkTheme, modifier = modifier) {
        val icon = if (isDarkMode) lightIcon else darkIcon
        icon?.let {
            Icon(bitmap = it, contentDescription = "Toggle Light/Dark Mode", tint = MaterialTheme.colors.tertiary)
        }
    }
}
