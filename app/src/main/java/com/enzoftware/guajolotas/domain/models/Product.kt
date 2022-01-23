package com.enzoftware.guajolotas.domain.models

import androidx.annotation.DrawableRes
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.data.local.ProductEntity

enum class ProductType {
    Guajolota,
    Drink,
    Tamal
}

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    var quantity: Int = 0,
    @DrawableRes
    val image: Int = R.drawable.champurrado,
    @DrawableRes
    val flavorImage: Int = R.drawable.flavor_coffee,
    val type: ProductType,
) {

    val formattedPrice: String
        get() = "$${price} PEN"

    fun increaseQuantity() {
        this.quantity += 1
    }

    fun decreaseQuantity() {
        if (this.quantity > 0) this.quantity -= 1
    }

    override fun toString(): String {
        return "Product $name, Price: $price, Quantity: $quantity"
    }

    fun isTamal() = this.type == ProductType.Tamal

    fun isGuajolota() = this.type == ProductType.Guajolota

    fun isDrink() = this.type == ProductType.Drink

    fun isFood() = isGuajolota() || isTamal()
}

fun Product.toEntity() = ProductEntity(
    id = id,
    name = name,
    price = price,
    quantity = quantity,
    image = image,
    flavorImage = flavorImage,
    type = type,
)

