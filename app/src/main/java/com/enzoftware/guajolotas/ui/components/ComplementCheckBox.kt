package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.theme.AppColors

@Composable
fun ComplementCheckBox(product: Product, onClick: (isChecked: Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                checkedState.value = !checkedState.value
                onClick(checkedState.value)
            }) {
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
                        painter = painterResource(id = if (checkedState.value) R.drawable.checked else R.drawable.unchecked),
                        contentDescription = "Checkbox state"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, fontSize = 12.sp, fontWeight = FontWeight.W600)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "+ ${product.formattedPrice}",
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                color = AppColors.primary
            )
        }
    }
}
