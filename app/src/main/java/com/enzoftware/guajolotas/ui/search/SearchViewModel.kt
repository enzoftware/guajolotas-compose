package com.enzoftware.guajolotas.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.usecase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableLiveData<SearchViewState>(SearchViewState.Initial)
    val state: LiveData<SearchViewState>
        get() = _state

    fun searchProduct(query: String) {
        emitSearchUiModel(SearchViewState.Loading)
        viewModelScope.launch(coroutineDispatchers.io) {
            val result = searchProductUseCase.searchProduct(query)
            withContext(coroutineDispatchers.main) {
                when (result) {
                    is ResultState.Success -> searchProductSuccess(result.data)
                    is ResultState.Error -> searchProductError(result.exception)
                }
            }
        }
    }

    private fun searchProductSuccess(data: List<Product>) {
        emitSearchUiModel(SearchViewState.Success(data))
    }

    private fun searchProductError(exception: Exception) {
        emitSearchUiModel(SearchViewState.Error(exception))
    }

    private fun emitSearchUiModel(searchViewState: SearchViewState) {
        _state.value = searchViewState
    }
}
