package org.chiachat.app.compose.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
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
) {
  val text: String by textProperty.collectAsState()
  OutlinedTextField(
      value = text,
      label = @Composable { label?.let { Text(it) } },
      placeholder = @Composable { placeholder?.let { Text(it) } },
      onValueChange = { newText: String -> textProperty.value = newText })
}

@Composable
fun CchActionButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
  OutlinedButton(
      onClick,
      colors =
          ButtonDefaults.outlinedButtonColors(
              containerColor = MaterialTheme.colorScheme.tertiary,
              contentColor = MaterialTheme.colorScheme.onTertiary),
      modifier = Modifier.width(300.dp).then(modifier)) {
        Text(text)
      }
}

@Composable
fun ToggleDarkModeButton(themeService: ThemeService, resourceService: ResourceService, modifier: Modifier = Modifier) {
  var darkIcon by mutableStateOf<ImageBitmap?>(null)
  var lightIcon by mutableStateOf<ImageBitmap?>(null)
  val isDarkMode by themeService.darkMode.collectAsState()

  LaunchedEffect(true) {
    resourceService.loadIcon(CchIcons.DARK_MODE) { darkIcon = it }
    resourceService.loadIcon(CchIcons.LIGHT_MODE) { lightIcon = it }
  }

  IconButton(onClick = themeService::toggleDarkTheme, modifier = modifier) {
    val icon = if (isDarkMode) darkIcon else lightIcon
    icon?.let { Icon(bitmap = it, contentDescription = "Toggle Light/Dark Mode") }
  }
}
