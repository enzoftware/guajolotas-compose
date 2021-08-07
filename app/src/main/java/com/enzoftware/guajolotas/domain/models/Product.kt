package com.enzoftware.guajolotas.domain.models

class Product(private val name: String, private val price: Double, private val quantity: Int) {
    override fun toString(): String {
        return "Product $name, Price: $price, Quantity: $quantity"
    }

    companion object {
        fun empty(name: String) = Product(name = name, price = 0.0, quantity = 0)
    }
}
