package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.ui.components.GuajolotaLogo
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun HomeScreen() {
    GuajolotasTheme {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {
            AppBar()
            Spacer(modifier = Modifier.height(32.dp))
            HomeHeader()
        }
    }
}

@Composable
fun AppBar() {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        GuajolotaLogo(modifier = Modifier.size(64.dp))
        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "Appbar"
        )
    }
}

@Composable
fun HomeHeader() {
    Text(
        text = "Nada como una Guajolota para empezar el día",
        fontSize = 34.sp,
        fontWeight = FontWeight.W700
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GuajolotasTheme {
        HomeHeader()
    }
}
