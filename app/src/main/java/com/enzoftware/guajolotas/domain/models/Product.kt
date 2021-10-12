package com.enzoftware.guajolotas.domain.models

import androidx.annotation.DrawableRes

enum class ProductType {
    Guajolota,
    Drink,
    Tamal
}

data class Product(
    val id: String,
    val name: String,
    private val price: Double,
    var quantity: Int = 0,
    @DrawableRes
    val image: Int,
    @DrawableRes
    val flavorImage: Int,
    private val type: ProductType,
) {

    val formattedPrice: String
        get() = "$${price} PEN"

    fun increaseQuantity() {
        this.quantity += 1
    }

    fun decreaseQuantity() {
        this.quantity -= 1
    }

    override fun toString(): String {
        return "Product $name, Price: $price, Quantity: $quantity"
    }

    fun isTamal() = this.type == ProductType.Tamal

    fun isGuajolota() = this.type == ProductType.Guajolota

    fun isDrink() = this.type == ProductType.Drink

    fun isFood() = isGuajolota() || isTamal()
}

