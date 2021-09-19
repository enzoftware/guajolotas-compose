package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import kotlinx.coroutines.delay

class ProductLocalDataSource {

    suspend fun fetchDrinks(): ResultState<List<Product>> {
        delay(2000)
        // Here replace with a real call from a data source
        val response = FakeProducts.drinks
        // Here you can validate if the response is Success or Error
        return ResultState.Success(response)
    }

    suspend fun fetchTamales(): ResultState<List<Product>> {
        delay(2000)
        val response = FakeProducts.tamales
        return ResultState.Success(response)
    }

    suspend fun fetchSandwiches(): ResultState<List<Product>> {
        delay(2000)
        val response = FakeProducts.guajolotas
        return ResultState.Success(response)
    }
}
