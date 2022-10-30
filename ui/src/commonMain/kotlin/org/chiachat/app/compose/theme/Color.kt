package org.chiachat.app.compose.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val green = Color(0x19b36a)
val greenVariant = Color(0x36bf83)

val navy = Color(0x042e6d)
val navyVariant = Color(0x065ddb)

val lightBlue = Color(0x36c2f3)

val tertiary = Color(0x69409e)

val chiachatLightScheme = lightColors(
    primary = Color(0x006d3d),
    onPrimary = Color(0xffffff),
    primaryVariant = Color(0x75fcab),
    secondary = Color(0x7249a8),
    onSecondary = Color(0xffffff),
    secondaryVariant = Color(0xeedcff),
    error = Color(0xba1a1a),
    onError = Color(0xffffff),
    background = Color(0xfefbff),
    onBackground = Color(0x001944),
    surface = Color(0xfefbff),
)

val chiachatDarkScheme = darkColors(
    primary = Color(0x56df91),
    onPrimary = Color(0x00391d),
    primaryVariant = Color(0x00522d),
    secondary = Color(0xd8b9ff),
    onSecondary = Color(0x421576),
    secondaryVariant = Color(0x5a308e),
    error = Color(0xffb4ab),
    onError = Color(0x690005),
    background = Color(0x001944),
    onBackground = Color(0xd9e2ff),
    surface = Color(0x001944),
)