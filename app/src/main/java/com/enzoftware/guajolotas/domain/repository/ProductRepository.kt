package com.enzoftware.guajolotas.domain.repository

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product

interface ProductRepository {

    suspend fun fetchDrinks(): ResultState<List<Product>>

    suspend fun fetchTamales(): ResultState<List<Product>>

    suspend fun fetchGuajolotas(): ResultState<List<Product>>
}


