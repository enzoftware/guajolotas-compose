package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.domain.repository.ShoppingCartRepository
import javax.inject.Inject

class GetShoppingCartUseCase @Inject constructor(private val shoppingCartRepository: ShoppingCartRepository) {

    suspend fun getShoppingCart() = shoppingCartRepository.getShoppingCartProducts()

}
