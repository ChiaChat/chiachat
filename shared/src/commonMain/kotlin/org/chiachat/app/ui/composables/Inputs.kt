package org.chiachat.app.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import org.chiachat.app.ui.services.ResourceService
import org.chiachat.app.ui.services.ThemeService
import org.chiachat.app.ui.theme.CchGraphics

@Composable
internal fun CchTextField(
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
internal fun CchActionButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick,
        colors =
        ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary
        ),
        modifier = Modifier.fillMaxWidth().then(modifier)
    ) {
        Text(text)
    }
}

@Composable
internal fun ToggleDarkModeButton(
    themeService: ThemeService,
    resources: ResourceService,
    modifier: Modifier = Modifier
) {
    val isDarkMode by themeService.darkMode.collectAsState()
    IconButton(onClick = themeService::toggleDarkTheme) {
        if (isDarkMode)
            Graphic(resources, CchGraphics.LIGHT_MODE, "Enable light mode")
        else
            Graphic(resources, CchGraphics.DARK_MODE, "Enable Dark Mode")
    }
}
