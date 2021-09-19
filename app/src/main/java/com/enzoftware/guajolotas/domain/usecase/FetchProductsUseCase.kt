package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.domain.repository.ProductRepository
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend fun getDrinkProducts() = productRepository.fetchDrinks()

    suspend fun getTamalesProducts() = productRepository.fetchTamales()

    suspend fun getGuajolotasProducts() = productRepository.fetchGuajolotas()
}
