package com.enzoftware.guajolotas.ui.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.GetShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(ShoppingCartUiModel())

    val state: StateFlow<ShoppingCartUiModel>
        get() = _state


    fun getShoppingCartProducts() {
        emitShoppingCartUiModel(loading = true)
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = getShoppingCartUseCase.getShoppingCart()
            withContext(coroutineDispatchers.main) {
                getShoppingCartSuccess(result)
            }
        }
    }

    private fun getShoppingCartError(exception: Exception) {
        TODO("Not yet implemented")
    }

    private fun getShoppingCartSuccess(data: Flow<List<Product>>) {
        
    }

    private fun emitShoppingCartUiModel(
        loading: Boolean = false,
        products: List<Product>? = null,
        exception: Exception? = null
    ) {
        val uiModel = ShoppingCartUiModel(loading, products, exception)
        _state.value = uiModel
    }
}


data class ShoppingCartUiModel(
    val loading: Boolean = false,
    val products: List<Product>? = null,
    val exception: Exception? = null,
)
