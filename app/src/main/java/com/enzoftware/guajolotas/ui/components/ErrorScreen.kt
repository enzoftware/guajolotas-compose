package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(exception: Exception) {
    Column {
        Text(text = exception.message ?: "Unknown error")
    }
}

@Preview
@Composable
fun ErrorScreenPreview(){
    ErrorScreen(exception = Exception("Problems here!"))
}
