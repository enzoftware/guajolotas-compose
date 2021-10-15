package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorScreen(exception: Exception) {
    Column {
        Text(text = exception.message ?: "Unknown error")
    }
}
