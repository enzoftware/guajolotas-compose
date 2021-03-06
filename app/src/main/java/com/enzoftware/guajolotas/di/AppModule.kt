package com.enzoftware.guajolotas.di

import android.content.Context
import androidx.room.Room
import com.enzoftware.guajolotas.core.CoroutineDispatchers
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
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatchers {
        return CoroutineDispatchers()
    }
}
