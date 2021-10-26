package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enzoftware.guajolotas.ui.ComposableFun
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun GuaButton(content: ComposableFun, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
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
            Row(Modifier) {
                Text(text = "Agregar 1 al carrito")
                Spacer(modifier = Modifier.width(48.dp))
                Text(text = "$12.00")
            }
        })
    }
}
