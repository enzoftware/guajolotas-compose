package com.enzoftware.guajolotas.domain.models


sealed class Product(
    val name: String,
    val price: Double,
    val quantity: Int
) {
    class Drink(
        name: String,
        price: Double,
        quantity: Int,
        val flavor: DrinkFlavors
    ) : Product(name, price, quantity)

    class Food(name: String, price: Double, quantity: Int, val flavor: FoodFlavors) :
        Product(name, price, quantity)

    override fun toString(): String {
        return "Product $name, Price: $price, Quantity: $quantity"
    }
}

enum class DrinkFlavors {
    chocolate,
    coffee,
    milk,
    mix
}

enum class FoodFlavors {
    green,
    pineapple,
    raisins,
    guayaba,
    mole
}
