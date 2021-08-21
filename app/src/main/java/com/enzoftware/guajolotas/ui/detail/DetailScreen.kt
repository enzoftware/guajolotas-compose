package com.enzoftware.guajolotas.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.data.FakeProducts
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.components.ProductCounter
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun DetailScreen(onBackPressed: () -> Unit) {
    Scaffold {
        Column(
            Modifier.padding(24.dp)
        ) {
            DetailAppBar()
            DetailBody(product = FakeProducts.mockFood)
        }
    }
}


@Composable
private fun DetailAppBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios),
            contentDescription = "Appbar"
        )
        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "Appbar"
        )
    }
}

@Composable
fun DetailBody(product: Product) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = "Product image",
            Modifier.size(160.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = product.name, fontSize = 28.sp, fontWeight = FontWeight.W700)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.formattedPrice,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            color = AppColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProductCounter(
            incrementProductCount = {},
            decreaseProductCount = {},
            count = product.quantity
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Sabor",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Preview
@Composable
fun DetailScreenPreview() {
    GuajolotasTheme {
        DetailScreen(onBackPressed = {})
    }
}
