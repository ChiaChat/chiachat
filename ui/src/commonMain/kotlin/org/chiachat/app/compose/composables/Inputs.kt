package org.chiachat.app.compose.composables

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CchTextField(textProperty: MutableStateFlow<String>) {
  val text: String by textProperty.collectAsState()
  TextField(value = text, onValueChange = { newText: String -> textProperty.value = newText })
}
