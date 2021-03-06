package com.enzoftware.guajolotas.domain.repository

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType

interface ProductRepository {

    suspend fun fetchProducts(): ResultState<List<Product>>

    suspend fun fetchProductByType(productType: ProductType): ResultState<List<Product>>

    suspend fun getProductDetail(id: String): ResultState<Product>

    suspend fun searchProduct(name: String): ResultState<List<Product>>
}


