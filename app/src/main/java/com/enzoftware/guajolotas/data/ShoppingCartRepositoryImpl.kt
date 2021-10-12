package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingCartRepositoryImpl @Inject constructor(private val localDataSource: ProductLocalDataSource) :
    ShoppingCartRepository {
    override suspend fun getShoppingCartProducts(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }
}
