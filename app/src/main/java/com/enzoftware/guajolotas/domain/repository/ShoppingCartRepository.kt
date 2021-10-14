package com.enzoftware.guajolotas.domain.repository

import com.enzoftware.guajolotas.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ShoppingCartRepository {

    suspend fun getShoppingCartProducts(): Flow<List<Product>>

    fun addProductToShoppingList(product: Product)

    suspend fun removeProductFromShoppingList(product: Product)

}
