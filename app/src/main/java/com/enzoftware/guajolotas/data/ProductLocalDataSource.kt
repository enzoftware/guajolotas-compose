package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.data.local.ProductDao
import com.enzoftware.guajolotas.data.local.toProduct
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.toEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(private val productDao: ProductDao) {

    suspend fun fetchProducts(): ResultState<List<Product>> {
        delay(DELAY_TIME)
        val response = FakeProducts.allProducts
        return ResultState.Success(response)
    }

    suspend fun fetchProductDetail(id: String): ResultState<Product> {
        delay(DELAY_TIME)
        val response = FakeProducts.allProducts.find { it.id == id }
        return ResultState.Success(response!!)
    }


    suspend fun searchProduct(name: String): ResultState<List<Product>> {
        delay(DELAY_TIME)
        val locale = Locale.getDefault()
        val response = FakeProducts.allProducts.filter {
            it.name.lowercase(locale).contains(name.lowercase(locale))
        }
        return ResultState.Success(response)
    }

    fun getShoppingCart(): Flow<List<Product>> {
        return productDao.getShoppingCart().map {
            it.map { entity -> entity.toProduct() }
        }
    }

    fun addProductToShoppingCart(product: Product) {
        productDao.addProduct(product.toEntity())
    }

    companion object {
        const val DELAY_TIME = 250L
    }

}
