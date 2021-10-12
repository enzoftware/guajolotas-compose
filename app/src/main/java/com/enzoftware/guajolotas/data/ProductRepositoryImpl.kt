package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.repository.ProductRepository

class ProductRepositoryImpl(val localDataSource: ProductLocalDataSource) : ProductRepository {

    override suspend fun fetchProducts() = localDataSource.fetchProducts()

    override suspend fun getProductDetail(id: String): ResultState<Product> =
        localDataSource.fetchProductDetail(id)

    override suspend fun searchProduct(name: String) = localDataSource.searchProduct(name)
}
