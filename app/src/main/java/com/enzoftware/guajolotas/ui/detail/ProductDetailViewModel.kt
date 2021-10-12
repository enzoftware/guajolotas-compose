package com.enzoftware.guajolotas.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailUiModel())

    val state: StateFlow<ProductDetailUiModel>
        get() = _state

    fun getProductDetail(id: String) {
        emitProductDetailUiModel(loading = true)
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = getProductDetailUseCase.getProductDetail(id)
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> getProductDetailSuccess(result.data)
                    is ResultState.Error -> getProductDetailError(result.exception)
                }
            }
        }
    }

    private fun getProductDetailSuccess(product: Product) {
        emitProductDetailUiModel(product = product)
    }

    private fun getProductDetailError(exception: Exception) {
        emitProductDetailUiModel(exception = exception)
    }

    private fun emitProductDetailUiModel(
        loading: Boolean = false,
        product: Product? = null,
        exception: Exception? = null
    ) {
        val uiModel = ProductDetailUiModel(loading, product, exception)
        _state.value = uiModel
    }

}


data class ProductDetailUiModel(
    val loading: Boolean = false,
    val product: Product? = null,
    val exception: Exception? = null
)
