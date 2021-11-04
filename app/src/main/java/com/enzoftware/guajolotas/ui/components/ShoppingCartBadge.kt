package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.ui.shopping.ShoppingCartUiModel
import com.enzoftware.guajolotas.ui.shopping.ShoppingCartViewModel
import com.enzoftware.guajolotas.ui.theme.AppColors

@ExperimentalMaterialApi
@Composable
fun ShoppingCartBadge(
    shoppingCartViewModel: ShoppingCartViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    val state by shoppingCartViewModel.state.collectAsState()

    shoppingCartViewModel.getShoppingCartProducts()

    when (state) {
        is ShoppingCartUiModel.Loading -> CircularProgressIndicator()
        is ShoppingCartUiModel.ShoppingCartSuccess -> {
            val productsLength = (state as ShoppingCartUiModel.ShoppingCartSuccess).products.size
            BadgeBox(
                badgeContent = {
                    if (productsLength > 0)
                        Text(
                            text = productsLength.toString(),
                            fontSize = 16.sp
                        )
                    else
                        Box {}
                },
                backgroundColor = AppColors.primary,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.shopping_cart),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClick()
                    }
                )
            }
        }
        is ShoppingCartUiModel.Error -> Box {}
    }
}
