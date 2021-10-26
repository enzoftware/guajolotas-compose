package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.repository.ShoppingCartRepository
import javax.inject.Inject

class AddProductToShoppingCart @Inject constructor(private val shoppingCartRepository: ShoppingCartRepository) {

    suspend fun addProduct(product: Product) {
        shoppingCartRepository.addProductToShoppingList(product)
    }
}
