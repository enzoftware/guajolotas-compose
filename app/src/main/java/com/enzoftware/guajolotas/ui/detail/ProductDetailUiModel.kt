package com.enzoftware.guajolotas.ui.detail

import com.enzoftware.guajolotas.domain.models.ProductDetailModel


sealed class ProductDetailUiModel {
    data object Loading : ProductDetailUiModel()
    data class ProductDetail(val productDetailModel: ProductDetailModel) :
        ProductDetailUiModel()

    data class Error(val exception: Exception) : ProductDetailUiModel()
}
