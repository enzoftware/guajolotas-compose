package com.enzoftware.guajolotas.domain.models

import androidx.annotation.DrawableRes
import com.enzoftware.guajolotas.R

enum class ProductType {
    Guajolota,
    Drink,
    Tamal
}

sealed class Product(
        val name: String,
        private val price: Double,
        var quantity: Int,
        @DrawableRes
        val image: Int,
        @DrawableRes
        val flavorImage: Int,
        private val type: ProductType,
) {
    class Drink(
            name: String,
            price: Double,
            quantity: Int = 0,
            image: Int,
            flavorImage: Int = R.drawable.shopping_cart,
            type: ProductType,
    ) : Product(name, price, quantity, image, flavorImage, type)

    class Food(
            name: String,
            price: Double,
            quantity: Int = 0,
            image: Int,
            flavorImage: Int = R.drawable.shopping_cart,
            type: ProductType,
    ) : Product(name, price, quantity, image, flavorImage, type)

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

    fun isDrink() = this.type == ProductType.Drink
    fun isTamal() = this.type == ProductType.Tamal
    fun isGuajolota() = this.type == ProductType.Guajolota
}

