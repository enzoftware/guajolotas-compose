package com.enzoftware.guajolotas.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.usecase.GetProductDetailUseCase
import com.enzoftware.guajolotas.domain.usecase.ProductDetailModel
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

    private val _state = MutableStateFlow<ProductDetailUiModel>(ProductDetailUiModel.Loading)

    val state: StateFlow<ProductDetailUiModel>
        get() = _state

    fun getProductDetail(id: String) {
        emitProductDetailUiModel(ProductDetailUiModel.Loading)
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

    private fun getProductDetailSuccess(product: ProductDetailModel) {
        emitProductDetailUiModel(ProductDetailUiModel.ProductDetail(product))
    }

    private fun getProductDetailError(exception: Exception) {
        emitProductDetailUiModel(ProductDetailUiModel.Error(exception))
    }

    private fun emitProductDetailUiModel(productDetailUiModel: ProductDetailUiModel) {
        _state.value = productDetailUiModel
    }
}
