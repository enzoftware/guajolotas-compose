package com.enzoftware.guajolotas.domain.models


sealed class Product(
    val name: String,
    val price: Double,
    var quantity: Int
) {
    class Drink(
        name: String,
        price: Double,
        quantity: Int = 0,
        val flavor: DrinkFlavors
    ) : Product(name, price, quantity)

    class Food(name: String, price: Double, quantity: Int = 0, val flavor: FoodFlavors) :
        Product(name, price, quantity)

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
