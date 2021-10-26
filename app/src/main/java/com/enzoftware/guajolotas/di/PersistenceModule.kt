package com.enzoftware.guajolotas.di

import android.content.Context
import androidx.room.Room
import com.enzoftware.guajolotas.data.ProductLocalDataSource
import com.enzoftware.guajolotas.data.local.GuajolotasDatabase
import com.enzoftware.guajolotas.data.local.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

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
}
