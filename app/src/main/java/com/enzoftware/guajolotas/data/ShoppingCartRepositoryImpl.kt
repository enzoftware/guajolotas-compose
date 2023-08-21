package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingCartRepositoryImpl @Inject constructor(private val productLocalDataSource: ProductLocalDataSource) :
    ShoppingCartRepository {

    override suspend fun getShoppingCartProducts(): Flow<List<Product>> {
        return productLocalDataSource.getShoppingCart()
    }

    override suspend fun addProductToShoppingList(product: Product) {
        productLocalDataSource.addProductToShoppingCart(product)
    }

    override suspend fun removeProductFromShoppingList(product: Product) {
        productLocalDataSource.removeProductFromShoppingCart(product)
    }
}
