package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun ProductCounter(
    incrementProductCount: () -> Unit,
    decreaseProductCount: () -> Unit,
    count: Int
) {
    Box(Modifier.background(color = Color.White, shape = RoundedCornerShape(20.dp))) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.minus_circle),
                contentDescription = "Decrease quantity",
                Modifier.clickable { decreaseProductCount() }
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(text = count.toString(), fontSize = 22.sp, fontWeight = FontWeight.W600)
            Spacer(modifier = Modifier.width(32.dp))
            Image(
                painter = painterResource(id = R.drawable.plus_circle),
                contentDescription = "Decrease quantity",
                Modifier.clickable { incrementProductCount() }
            )
        }
    }
}

@Preview
@Composable
fun ProductCounterPreview() {
    GuajolotasTheme {
        ProductCounter(decreaseProductCount = {}, incrementProductCount = {}, count = 12)
    }
}
