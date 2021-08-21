package com.enzoftware.guajolotas.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun SearchScreen() {
    Scaffold() {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 40.dp)) {
            SearchTopBar()
        }
    }
}

@Composable
fun SearchTopBar() {
    var text by remember { mutableStateOf("") }
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Tamal, Cafe, ...") },
            shape = RoundedCornerShape(30.dp),
            trailingIcon = { Icon(Icons.Filled.Search, "Search icon") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = AppColors.textFieldBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.weight(12f)
        )
        Text(
            text = "Cancelar",
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .weight(4f)
                .padding(start = 20.dp)
        )
    }
}

@Composable
fun SearchInitialScreen() {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.feather_search),
            contentDescription = "Search icon",
            modifier = Modifier
                .width(100.dp)
                .height(120.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Realiza una búsqueda", fontSize = 16.sp, fontWeight = FontWeight.W600)
    }
}

@Composable
fun SearchEmptyScreen() {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.feather_search),
            contentDescription = "Search icon",
            modifier = Modifier
                .width(100.dp)
                .height(120.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "No hay resultados", fontSize = 16.sp, fontWeight = FontWeight.W600)
    }
}

@Preview
@Composable
fun SearchTopBarPreview() {
    GuajolotasTheme {
        SearchScreen()
    }
}
