package org.chiachat.app.compose.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.flow.MutableStateFlow

// Set of Material typography styles to start with

/*suspend fun loadFont(file: VfsFile): Font {
  val name = file.baseNameWithoutExtension
  val fontData = file.readAll()
  val weight = fontToWeight(name.split("-").lastOrNull() ?: "")
  return Font(identity = file.baseNameWithoutExtension, data = fontData, weight = weight)
}*/

fun fontToWeight(name: String): FontWeight {
  return when (name.lowercase()) {
    "semibold" -> FontWeight.SemiBold
    "bold" -> FontWeight.Bold
    "extrabold" -> FontWeight.ExtraBold
    "light" -> FontWeight.Light
    "regular" -> FontWeight.Normal
    "normal" -> FontWeight.Normal
    "thin" -> FontWeight.Thin
    "extralight" -> FontWeight.ExtraLight
    else -> FontWeight.Normal
  }
}

/*suspend fun loadFontFamily(name: String): FontFamily {
  val fonts =
      resourcesVfs["fonts"]
          .listRecursiveSimple()
          .filter { it.isFile() && it.baseName.lowercase().contains(name.lowercase()) }
          .map { loadFont(it) }
  return if (fonts.isEmpty()) FontFamily.Default else FontFamily(fonts)
}*/

private val defaultFontFamily = MutableStateFlow<FontFamily>(FontFamily.Default)

// @Composable
// fun ChiaChatTypography(resources: ThemeResources): Typography {
//  return Typography(
//      defaultFontFamily = resources.defaultFont,
//      h4 = TextStyle(fontWeight = FontWeight.W600, fontSize = 30.sp),
//      h5 = TextStyle(fontWeight = FontWeight.W600, fontSize = 24.sp),
//      h6 = TextStyle(fontWeight = FontWeight.W600, fontSize = 20.sp),
//      subtitle1 = TextStyle(fontWeight = FontWeight.W600, fontSize = 16.sp),
//      subtitle2 = TextStyle(fontWeight = FontWeight.W500, fontSize = 14.sp),
//      body1 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
//      body2 = TextStyle(fontSize = 14.sp),
//      button = TextStyle(fontWeight = FontWeight.W500, fontSize = 14.sp),
//      caption = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp),
//      overline = TextStyle(fontWeight = FontWeight.W500, fontSize = 12.sp))
// }
