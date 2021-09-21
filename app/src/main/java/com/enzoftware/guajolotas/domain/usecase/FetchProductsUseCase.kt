package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.repository.ProductRepository
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend fun getDrinkProducts(): ResultState<List<Product>> {
        val products = productRepository.fetchProducts()
        if (products is ResultState.Success) {
            val drinks = products.data.filter { it.isDrink() }
            return ResultState.Success(drinks)
        }
        return products
    }

    suspend fun getTamalesProducts(): ResultState<List<Product>> {
        val products = productRepository.fetchProducts()
        if (products is ResultState.Success) {
            val tamales = products.data.filter { it.isTamal() }
            return ResultState.Success(tamales)
        }
        return products
    }

    suspend fun getGuajolotasProducts(): ResultState<List<Product>> {
        val products = productRepository.fetchProducts()
        if (products is ResultState.Success) {
            val guajolotas = products.data.filter { it.isGuajolota() }
            return ResultState.Success(guajolotas)
        }
        return products
    }
}
