package com.enzoftware.guajolotas.domain.models

import androidx.annotation.DrawableRes
import com.enzoftware.guajolotas.R


sealed class Product(
    val name: String,
    val price: Double,
    var quantity: Int,
    @DrawableRes
    val image: Int,
    @DrawableRes
    val flavorImage: Int
) {
    class Drink(
        name: String,
        price: Double,
        quantity: Int = 0,
        image: Int,
        flavorImage: Int = R.drawable.shopping_cart,
        val flavor: DrinkFlavors
    ) : Product(name, price, quantity, image, flavorImage)

    class Food(
        name: String,
        price: Double,
        quantity: Int = 0,
        image: Int,
        flavorImage: Int = R.drawable.shopping_cart,
        val flavor: FoodFlavors
    ) :
        Product(name, price, quantity, image, flavorImage)

    val formattedPrice: String = "$${price} PEN"

    fun increaseQuantity() {
        this.quantity += 1
    }

    fun decreaseQuantity() {
        this.quantity -= 1
    }

    override fun toString(): String {
        return "Product $name, Price: $price, Quantity: $quantity"
    }
}

enum class DrinkFlavors {
    Chocolate,
    Coffee,
    Milk,
    Mix
}

enum class FoodFlavors {
    Green,
    Pineapple,
    Raisins,
    Guayaba,
    Mole
}
