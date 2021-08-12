package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.domain.models.FoodFlavors
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.Product.Food
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun ProductItem(product: Product) {
    Card(modifier = Modifier.height(height = 112.dp), shape = RoundedCornerShape(20.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            GuajolotaLogo(modifier = Modifier.size(80.dp))
            Column {
                Text(text = product.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$${product.price} PEN", fontSize = 14.sp, color = Color.Blue)
            }
        }
    }
}


@Preview
@Composable
private fun ProductItemPreview() {
    val product =
        Food("Veggie tomato mix", price = 25.0, quantity = 0, flavor = FoodFlavors.pineapple)
    GuajolotasTheme {
        ProductItem(product = product)
    }
}