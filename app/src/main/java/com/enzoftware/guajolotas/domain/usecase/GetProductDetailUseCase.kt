package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend fun getProductDetail(id: String) = productRepository.getProductDetail(id)

}
