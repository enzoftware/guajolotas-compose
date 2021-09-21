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

    private val refreshing = MutableStateFlow(false)

    val state: StateFlow<HomeViewState>
        get() = _state

    fun getDrinks() {
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = fetchProductsUseCase.getDrinkProducts()
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> getDrinksSuccess()
                    is ResultState.Error -> getDrinksError()
                }
            }
        }
    }

    fun getTamales() {
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = fetchProductsUseCase.getTamalesProducts()
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> getDrinksSuccess()
                    is ResultState.Error -> getDrinksError()
                }
            }
        }
    }

    fun getGuajolotas() {
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = fetchProductsUseCase.getGuajolotasProducts()
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> getDrinksSuccess()
                    is ResultState.Error -> getDrinksError()
                }
            }
        }
    }

    private fun getDrinksError() {
        TODO("Not yet implemented")
    }

    private fun getDrinksSuccess() {
        TODO("Not yet implemented")
    }

}

data class HomeViewState(
    val products: List<Product> = emptyList(),
    val selectedProductIndex: Int = 0,
    val error: String? = null
)
