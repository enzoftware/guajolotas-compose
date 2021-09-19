package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.domain.repository.ProductRepository

class ProductRepositoryImpl(val localDataSource: ProductLocalDataSource) : ProductRepository {

    override suspend fun fetchDrinks() = localDataSource.fetchDrinks()

    override suspend fun fetchTamales() = localDataSource.fetchTamales()

    override suspend fun fetchGuajolotas() = localDataSource.fetchSandwiches()
}
