package com.enzoftware.guajolotas.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.components.ErrorScreen
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {

    val state by viewModel.state.observeAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 40.dp)
                .fillMaxSize()
        ) {
            SearchTopBar(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            when (state) {
                is SearchViewState.Initial -> SearchInitialScreen()
                is SearchViewState.Loading -> LoadingScreen()
                is SearchViewState.Success -> SearchProductsResult((state as SearchViewState.Success).products)
                is SearchViewState.Error -> ErrorScreen((state as SearchViewState.Error).exception)
            }
        }
    }
}

@Composable
fun SearchProductsResult(products: List<Product>) {
    if (products.isNotEmpty()) {
        LazyColumn {
            items(products) { product ->
                ProductItem(product = product) {

                }
            }
        }
    } else {
        SearchEmptyScreen()
    }
}

@Composable
fun SearchTopBar(viewModel: SearchViewModel) {
    var text by remember { mutableStateOf("") }
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.searchProduct(text)
            },
            label = { Text(stringResource(R.string.search_product)) },
            shape = RoundedCornerShape(30.dp),
            trailingIcon = { Icon(Icons.Filled.Search, stringResource(R.string.search_icon)) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = AppColors.textFieldBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            modifier = Modifier.weight(12f)
        )
        Text(
            text = stringResource(R.string.cancel),
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
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.feather_search),
            contentDescription = stringResource(R.string.search_icon_description),
            modifier = Modifier
                .width(100.dp)
                .height(120.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.empty_results),
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview
@Composable
fun SearchTopBarPreview() {
    GuajolotasTheme {
        SearchScreen()
    }
}
