package com.enzoftware.guajolotas.ui.shopping

import com.enzoftware.guajolotas.domain.models.Product

sealed class ShoppingCartUiModel {
    object Loading : ShoppingCartUiModel()
    data class ShoppingCartSuccess(val products: List<Product>) : ShoppingCartUiModel()
    data class Error(val exception: Exception) : ShoppingCartUiModel()
}
