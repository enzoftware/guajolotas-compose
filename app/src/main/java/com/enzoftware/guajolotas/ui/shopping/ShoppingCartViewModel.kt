package com.enzoftware.guajolotas.ui.shopping

import androidx.lifecycle.ViewModel
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.GetShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {


    val state = MutableStateFlow(ShoppingCartUiModel())

    fun getShoppingCartProducts() {

    }

    private fun getShoppingCartError(exception: Exception) {
        TODO("Not yet implemented")
    }

    private fun shoppingCartProducts() {

    }

    private fun emitShoppingCartUiModel(
        loading: Boolean = false,
        products: Flow<List<Product>>? = null,
        exception: Exception? = null
    ) {
        val uiModel = ShoppingCartUiModel(loading, products, exception)
        state.value = uiModel
    }
}


data class ShoppingCartUiModel(
    val loading: Boolean = false,
    val products: Flow<List<Product>>? = null,
    val exception: Exception? = null,
)
