package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.domain.repository.ProductRepository

class ProductRepositoryImpl(val localDataSource: ProductLocalDataSource) : ProductRepository {

    override suspend fun fetchProducts() = localDataSource.fetchProducts()

    override suspend fun fetchProductByType(productType: ProductType): ResultState<List<Product>> {
        val products = localDataSource.fetchProducts()
        if (products is ResultState.Success) {
            val filteredProducts = products.data.filter { it.type == productType }
            return ResultState.Success(filteredProducts)
        }
        return ResultState.Error(Exception("Product not found"))
    }

    override suspend fun getProductDetail(id: String): ResultState<Product> =
        localDataSource.fetchProductDetail(id)

    override suspend fun searchProduct(name: String) = localDataSource.searchProduct(name)
}
