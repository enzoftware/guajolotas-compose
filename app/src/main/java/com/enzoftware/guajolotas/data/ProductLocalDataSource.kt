package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.data.local.ProductDao
import com.enzoftware.guajolotas.data.local.toProduct
import com.enzoftware.guajolotas.domain.models.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(private val productDao: ProductDao) {

    suspend fun fetchProducts(): ResultState<List<Product>> {
        delay(2000)
        val response = FakeProducts.allProducts
        return ResultState.Success(response)
    }

    suspend fun fetchProductDetail(id: String): ResultState<Product> {
        delay(1000)
        val response = FakeProducts.allProducts.find { it.id == id }
        return ResultState.Success(response!!)
    }


    suspend fun searchProduct(name: String): ResultState<List<Product>> {
        delay(1500)
        val locale = Locale.getDefault()
        val response = FakeProducts.allProducts.filter {
            it.name.lowercase(locale).contains(name.lowercase(locale))
        }
        return ResultState.Success(response)
    }

    suspend fun getShoppingCart(): Flow<List<Product>> {
        return productDao.getShoppingCart().map {
            it.map { entity -> entity.toProduct() }
        }
    }

}
