package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.theme.AppColors

@Composable
fun GuaCheckBox(product: Product, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "Product image",
                    Modifier
                        .height(64.dp)
                        .width(64.dp)
                )
                Box(
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp),
                    contentAlignment = Alignment.TopEnd,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unchecked),
                        contentDescription = "Checkbox state"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, fontSize = 12.sp, fontWeight = FontWeight.W600)
            Spacer(modifier = Modifier.height(4.dp))
            // TODO: Handle currency inside the product model
            Text(
                text = "+ ${product.formattedPrice}",
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                color = AppColors.primary
            )
        }
    }
}
