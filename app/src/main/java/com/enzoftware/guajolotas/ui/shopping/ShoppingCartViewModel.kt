package com.enzoftware.guajolotas.ui.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.GetShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {


    val state = MutableStateFlow(ShoppingCartUiModel())

    fun getShoppingCartProducts() {
        viewModelScope.launch {
            getShoppingCartUseCase.getShoppingCart().collect { products ->
                emitShoppingCartUiModel(products = products)
            }
        }
    }

    private fun emitShoppingCartUiModel(
        loading: Boolean = false,
        products: List<Product>? = null,
        exception: Exception? = null
    ) {
        val uiModel = ShoppingCartUiModel(loading, products, exception)
        state.value = uiModel
    }
}


data class ShoppingCartUiModel(
    val loading: Boolean = false,
    val products: List<Product>? = null,
    val exception: Exception? = null,
)
