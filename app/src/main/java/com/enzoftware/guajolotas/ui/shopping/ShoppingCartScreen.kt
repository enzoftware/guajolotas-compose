package com.enzoftware.guajolotas.ui.shopping

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.ui.components.ErrorScreen
import com.enzoftware.guajolotas.ui.components.GuaButton
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import kotlin.random.Random

@Composable
fun ShoppingCartScreen(shoppingCartViewModel: ShoppingCartViewModel = hiltViewModel()) {

    val state by shoppingCartViewModel.state.collectAsState()

    shoppingCartViewModel.getShoppingCartProducts()

    Scaffold {
        when (state) {
            is ShoppingCartUiModel.Loading -> LoadingScreen()
            is ShoppingCartUiModel.ShoppingCartSuccess -> Column {
                val products = (state as ShoppingCartUiModel.ShoppingCartSuccess).products
                Text(text = "Carrito")
                LazyColumn {
                    items(products) { product ->
                        ProductItem(product = product) {

                        }
                    }
                }
                GuaButton(content = { Text(text = "Agregar producto al carrito") }) {
                    val p = Product(
                        "id-${Random(1000)}",
                        "name",
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
