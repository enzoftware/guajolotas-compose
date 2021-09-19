package com.enzoftware.guajolotas.di

import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.data.ProductLocalDataSource
import com.enzoftware.guajolotas.data.ProductRepositoryImpl
import com.enzoftware.guajolotas.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatchers {
        return CoroutineDispatchers()
    }


    @Provides
    @Singleton
    fun provideProductLocalDataSource(): ProductLocalDataSource {
        return ProductLocalDataSource()

    }

    @Provides
    @Singleton
    fun provideProductRepository(localDataSource: ProductLocalDataSource): ProductRepository {
        return ProductRepositoryImpl(localDataSource = localDataSource)
    }

}
