package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun GuaButton(content: @Composable() () -> Unit, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        Modifier.height(64.dp),
        shape = RoundedCornerShape(40.dp),
    ) {
        content()
    }
}

@Preview
@Composable
fun GuaButtonPreview() {
    GuajolotasTheme {
        GuaButton(onClick = {}, content = {
            Text(text = "Agregar 1 al carrito")
        })
    }
}
