package com.enzoftware.guajolotas.ui.home

import androidx.lifecycle.ViewModel
import com.enzoftware.guajolotas.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())

    private val refreshing = MutableStateFlow(false)

    val state: StateFlow<HomeViewState>
        get() = _state


}

data class HomeViewState(
    val products: List<Product> = emptyList(),
    val selectedProductIndex: Int = 0,
    val error: String? = null
)
