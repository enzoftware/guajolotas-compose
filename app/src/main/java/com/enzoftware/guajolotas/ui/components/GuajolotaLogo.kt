package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun GuajolotaLogo(modifier: Modifier) {
    Image(
        painterResource(id = R.drawable.logo),
        contentDescription = "App logo",
        modifier = modifier
    )
}


@Preview
@Composable
private fun LogoPreview() {
    GuajolotasTheme {
        GuajolotaLogo(modifier = Modifier.size(200.dp))
    }
}
