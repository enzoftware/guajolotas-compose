package com.enzoftware.guajolotas.domain.models

import androidx.annotation.DrawableRes


sealed class Product(
    val name: String,
    val price: Double,
    var quantity: Int,
    @DrawableRes
    val image: Int
) {
    class Drink(
        name: String,
        price: Double,
        quantity: Int = 0,
        image: Int,
        val flavor: DrinkFlavors
    ) : Product(name, price, quantity, image)

    class Food(
        name: String,
        price: Double,
        quantity: Int = 0,
        image: Int,
        val flavor: FoodFlavors
    ) :
        Product(name, price, quantity, image)

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
