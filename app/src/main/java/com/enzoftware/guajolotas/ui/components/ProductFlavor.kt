package com.enzoftware.guajolotas.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProductFlavor(@DrawableRes image: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Product flavor",
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    )
}
