package com.enzoftware.guajolotas.ui.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.AddProductToShoppingCart
import com.enzoftware.guajolotas.domain.usecase.GetShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val addProductToShoppingCart: AddProductToShoppingCart,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow<ShoppingCartUiModel>(ShoppingCartUiModel.Loading)

    val state: StateFlow<ShoppingCartUiModel>
        get() = _state

    fun getShoppingCartProducts() {
        emitShoppingCartUiModel(ShoppingCartUiModel.Loading)
        viewModelScope.launch(coroutineDispatchers.io) {
            val cartProductsFlow = getShoppingCartUseCase.getShoppingCart()
            try {
                cartProductsFlow.collect { products ->
                    if (products.isEmpty()) emitShoppingCartUiModel(ShoppingCartUiModel.Empty)
                    else emitShoppingCartUiModel(ShoppingCartUiModel.ShoppingCartSuccess(products))
                }
            } catch (e: Exception) {
                emitShoppingCartUiModel(ShoppingCartUiModel.Error(e))
            }
        }
    }

    fun addProductToShoppingList(product: Product) {
        viewModelScope.launch(coroutineDispatchers.io) {
            addProductToShoppingCart.addProduct(product)
        }
    }

    private fun emitShoppingCartUiModel(shoppingCartUiModel: ShoppingCartUiModel) {
        _state.value = shoppingCartUiModel
    }
}
