package com.enzoftware.guajolotas.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.FetchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    fun getDrinks() {
        emitHomeViewState(loading = true)
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = fetchProductsUseCase.getDrinkProducts()
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> getProductsSuccess(result.data)
                    is ResultState.Error -> getProductsError(result.exception)
                }
            }
        }
    }

    fun getTamales() {
        emitHomeViewState(loading = true)
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = fetchProductsUseCase.getTamalesProducts()
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> getProductsSuccess(result.data)
                    is ResultState.Error -> getProductsError(result.exception)
                }
            }
        }
    }

    fun getGuajolotas() {
        emitHomeViewState(loading = true)
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = fetchProductsUseCase.getGuajolotasProducts()
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> getProductsSuccess(result.data)
                    is ResultState.Error -> getProductsError(result.exception)
                }
            }
        }
    }

    private fun getProductsError(exception: Exception) {
        emitHomeViewState(exception = exception)
    }

    private fun getProductsSuccess(products: List<Product>) {
        emitHomeViewState(products = products)
    }

    private fun emitHomeViewState(
        products: List<Product>? = null,
        loading: Boolean = false,
        exception: Exception? = null,
    ) {
        val uiModel = HomeViewState(products, loading, exception.toString())
        _state.value = uiModel
    }

}

data class HomeViewState(
    val products: List<Product>? = null,
    val loading: Boolean = false,
    val error: String? = null
)
