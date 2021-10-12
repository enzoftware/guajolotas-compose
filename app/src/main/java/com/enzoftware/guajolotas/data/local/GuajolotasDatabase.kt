package com.enzoftware.guajolotas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = GuajolotasDatabase.DATABASE_VERSION)
abstract class GuajolotasDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}
