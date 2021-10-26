package com.enzoftware.guajolotas.ui.search

import com.enzoftware.guajolotas.domain.models.Product

sealed class SearchViewState {
    object Initial : SearchViewState()
    object Loading : SearchViewState()
    data class Success(val products: List<Product>) : SearchViewState()
    data class Error(val exception: Exception) : SearchViewState()
}

