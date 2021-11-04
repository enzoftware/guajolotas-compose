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

    private val _state = MutableStateFlow<HomeUiModel>(HomeUiModel.Loading)
    val state: StateFlow<HomeUiModel>
        get() = _state

    init {
        getGuajolotas()
    }

    fun getDrinks() {
        emitHomeViewState(HomeUiModel.Loading)
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
        emitHomeViewState(HomeUiModel.Loading)
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
        emitHomeViewState(HomeUiModel.Loading)
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
        emitHomeViewState(HomeUiModel.Error(exception))
    }

    private fun getProductsSuccess(products: List<Product>) {
        emitHomeViewState(HomeUiModel.ProductsSuccess(products))
    }

    private fun emitHomeViewState(homeUiModel: HomeUiModel) {
        _state.value = homeUiModel
    }

}
