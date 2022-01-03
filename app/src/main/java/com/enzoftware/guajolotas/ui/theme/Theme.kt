package com.enzoftware.guajolotas.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.enzoftware.guajolotas.ui.ComposableFun


private val colors = lightColors(
    primary = AppColors.primary,
    secondary = Color.Green,
    onError = Color.Red,
    background = AppColors.background

)

@Composable
fun GuajolotasTheme(content: ComposableFun) {
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
