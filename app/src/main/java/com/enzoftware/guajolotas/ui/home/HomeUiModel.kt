package com.enzoftware.guajolotas.ui.home

import com.enzoftware.guajolotas.domain.models.Product


sealed class HomeUiModel {
    object Loading : HomeUiModel()
    data class ProductsSuccess(val products: List<Product>) : HomeUiModel()
    data class Error(val exception: Exception) : HomeUiModel()
}
