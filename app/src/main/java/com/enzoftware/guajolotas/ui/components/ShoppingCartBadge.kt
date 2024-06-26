package com.enzoftware.guajolotas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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

@Composable
fun ShoppingCartBadge(
    shoppingCartViewModel: ShoppingCartViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    val state by shoppingCartViewModel.state.collectAsState()

    shoppingCartViewModel.getShoppingCartProducts()

    when (state) {
        is ShoppingCartUiModel.Loading -> CircularProgressIndicator()
        is ShoppingCartUiModel.Empty,
        is ShoppingCartUiModel.ShoppingCartSuccess -> {
            BadgedBox(
                badge = {
                    if (state is ShoppingCartUiModel.Empty)
                        Box {}
                    else {
                        val size = (state as ShoppingCartUiModel.ShoppingCartSuccess).products.size
                        Text(
                            text = size.toString(),
                            fontSize = 16.sp,
                            color = AppColors.primary,
                        )
                    }
                }
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
