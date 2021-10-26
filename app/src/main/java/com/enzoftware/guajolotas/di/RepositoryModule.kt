package com.enzoftware.guajolotas.di

import com.enzoftware.guajolotas.data.ProductLocalDataSource
import com.enzoftware.guajolotas.data.ProductRepositoryImpl
import com.enzoftware.guajolotas.data.ShoppingCartRepositoryImpl
import com.enzoftware.guajolotas.domain.repository.ProductRepository
import com.enzoftware.guajolotas.domain.repository.ShoppingCartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(localDataSource: ProductLocalDataSource): ProductRepository {
        return ProductRepositoryImpl(localDataSource = localDataSource)
    }

    @Provides
    @Singleton
    fun provideShoppingCartRepository(localDataSource: ProductLocalDataSource): ShoppingCartRepository {
        return ShoppingCartRepositoryImpl(productLocalDataSource = localDataSource)
    }
}
