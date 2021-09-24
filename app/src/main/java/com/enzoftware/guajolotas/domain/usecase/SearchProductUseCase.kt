package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.domain.repository.ProductRepository
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend fun invoke(query: String) = productRepository.searchProduct(query)
}
