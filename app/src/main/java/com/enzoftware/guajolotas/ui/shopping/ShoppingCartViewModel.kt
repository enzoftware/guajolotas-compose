package com.enzoftware.guajolotas.ui.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(private val coroutineDispatchers: CoroutineDispatchers) :
    ViewModel() {

    private val _state = MutableStateFlow(ShoppingCartUiModel())

    val state: StateFlow<ShoppingCartUiModel>
        get() = _state


    fun getShoppingCartProducts() {
        viewModelScope.launch(coroutineDispatchers.io) { }
    }
}


data class ShoppingCartUiModel(
    val loading: Boolean = false,
    val products: List<Product>? = null,
    val exception: Exception? = null,
)
