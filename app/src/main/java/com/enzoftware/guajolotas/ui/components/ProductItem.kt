package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.theme.AppColors

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(112.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Image(
                painter = painterResource(id = product.image),
                modifier = Modifier.size(80.dp),
                contentDescription = "Product image"
            )
            Column {
                Text(text = product.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.formattedPrice, fontSize = 14.sp, color = AppColors.primary)
            }
        }
    }
}
