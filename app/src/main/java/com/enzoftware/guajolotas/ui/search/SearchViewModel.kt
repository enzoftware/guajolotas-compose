package com.enzoftware.guajolotas.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiModel())
    val state: StateFlow<SearchUiModel>
        get() = _state

    fun searchProduct(query: String) {
        emitSearchUiModel(loading = true)
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = searchProductUseCase.invoke(query)
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> searchProductSuccess(result.data)
                    is ResultState.Error -> searchProductError(result.exception)
                }
            }
        }
    }

    private fun searchProductSuccess(data: List<Product>) {
        emitSearchUiModel(products = data)
    }

    private fun searchProductError(exception: Exception) {
        emitSearchUiModel(exception = exception)
    }

    private fun emitSearchUiModel(
        loading: Boolean = false,
        products: List<Product>? = null,
        exception: Exception? = null
    ) {
        val uiModel = SearchUiModel(loading, products, exception)
        _state.value = uiModel
    }
}

data class SearchUiModel(
    val loading: Boolean = false,
    val products: List<Product>? = null,
    val exception: Exception? = null,
)
