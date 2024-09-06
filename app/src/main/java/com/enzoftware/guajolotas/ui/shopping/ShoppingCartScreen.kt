@file:OptIn(ExperimentalMaterial3Api::class)

package com.enzoftware.guajolotas.ui.shopping

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.ui.components.ErrorScreen
import com.enzoftware.guajolotas.ui.components.GuaButton
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.search.SearchEmptyScreen
import kotlin.random.Random

@Composable
fun ShoppingCartScreen(shoppingCartViewModel: ShoppingCartViewModel = hiltViewModel()) {

    val state by shoppingCartViewModel.state.collectAsState()

    shoppingCartViewModel.getShoppingCartProducts()

    Scaffold(topBar = { TopAppBar(title = { Text("Carrito") }) }) { padding ->

        when (state) {
            is ShoppingCartUiModel.Loading -> LoadingScreen()
            is ShoppingCartUiModel.Empty -> SearchEmptyScreen()
            is ShoppingCartUiModel.ShoppingCartSuccess -> Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val products = (state as ShoppingCartUiModel.ShoppingCartSuccess).products
                Text(
                    text = stringResource(R.string.shopping_cart),
                    modifier = Modifier.padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
                LazyColumn(Modifier.weight(1f)) {
                    items(products) { product ->
                        ProductItem(product = product) {

                        }
                    }
                }
                GuaButton(
                    content = { Text(text = stringResource(R.string.add_product_to_shopping_cart)) },
                    modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth()
                ) {
                    val id = Random.nextInt(10, 10000)
                    val p = Product(
                        "id-$id",
                        "name-$id",
                        12.0,
                        1,
                        R.drawable.coffee,
                        R.drawable.flavor_guayaba,
                        ProductType.Tamal
                    )
                    shoppingCartViewModel.addProductToShoppingList(p)
                }
            }

            is ShoppingCartUiModel.Error -> ErrorScreen(exception = (state as ShoppingCartUiModel.Error).exception)
        }
    }
}
