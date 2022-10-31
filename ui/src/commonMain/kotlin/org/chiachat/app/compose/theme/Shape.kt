package org.chiachat.app.compose.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val ChiaChatShapes =
    Shapes(
        small = CutCornerShape(topStart = 8.dp),
        medium = CutCornerShape(topStart = 24.dp),
        large = RoundedCornerShape(8.dp))
