package com.enzoftware.guajolotas.di

import android.content.Context
import androidx.room.Room
import com.enzoftware.guajolotas.core.CoroutineDispatchers
import com.enzoftware.guajolotas.data.ProductLocalDataSource
import com.enzoftware.guajolotas.data.ProductRepositoryImpl
import com.enzoftware.guajolotas.data.ShoppingCartRepositoryImpl
import com.enzoftware.guajolotas.data.local.GuajolotasDatabase
import com.enzoftware.guajolotas.data.local.ProductDao
import com.enzoftware.guajolotas.domain.repository.ProductRepository
import com.enzoftware.guajolotas.domain.repository.ShoppingCartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): GuajolotasDatabase {
        return Room.databaseBuilder(
            appContext,
            GuajolotasDatabase::class.java,
            "GuajolotasDatabase"
        ).build()
    }

    @Provides
    fun provideProductDao(database: GuajolotasDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: ProductDao): ProductLocalDataSource {
        return ProductLocalDataSource(dao)
    }

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
