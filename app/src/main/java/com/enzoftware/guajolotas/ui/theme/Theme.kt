package com.enzoftware.guajolotas.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.enzoftware.guajolotas.ui.ComposableFun


private val colors = lightColorScheme(
    primary = AppColors.primary,
    secondary = Color.Green,
    onError = Color.Red,
    background = AppColors.background,


    )

@Composable
fun GuajolotasTheme(content: ComposableFun) {
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
