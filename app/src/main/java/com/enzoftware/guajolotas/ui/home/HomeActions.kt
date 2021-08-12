package com.enzoftware.guajolotas.ui.home

sealed class HomeActions {
    data class ProductDetail(val productId: String) : HomeActions()
}
