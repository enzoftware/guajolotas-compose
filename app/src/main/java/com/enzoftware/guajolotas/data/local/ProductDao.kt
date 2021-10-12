package com.enzoftware.guajolotas.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getShoppingCart() : Flow<List<ProductEntity>>
}
