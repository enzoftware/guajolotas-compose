package com.enzoftware.guajolotas.data.local

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType

@Entity(tableName = ProductEntity.DATABASE_NAME)
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    @DrawableRes
    val image: Int,
    @DrawableRes
    val flavorImage: Int,
    val type: ProductType,
) {

    companion object {
        const val DATABASE_NAME = "products"
    }
}

fun ProductEntity.toProduct() = Product(
    id = id,
    name = name,
    price = price,
    quantity = quantity,
    image = image,
    flavorImage = flavorImage,
    type = type
)
