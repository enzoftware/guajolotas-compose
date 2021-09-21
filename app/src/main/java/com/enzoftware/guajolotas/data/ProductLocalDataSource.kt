package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import kotlinx.coroutines.delay

class ProductLocalDataSource {

    suspend fun fetchProducts(): ResultState<List<Product>> {
        delay(2000)
        val response = FakeProducts.allProducts
        return ResultState.Success(response)
    }


    suspend fun searchProduct(name: String): ResultState<List<Product>> {
        delay(2000)
        val response = FakeProducts.allProducts.filter { it.name.contains(name) }
        return ResultState.Success(response)
    }

}
