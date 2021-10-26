package com.enzoftware.guajolotas.ui.detail

import com.enzoftware.guajolotas.domain.models.Product

sealed class ProductDetailUiModel {
    object Loading: ProductDetailUiModel()
    data class ProductDetail(val product: Product): ProductDetailUiModel()
    data class Error(val exception: Exception): ProductDetailUiModel()
}
