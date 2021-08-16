package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.domain.models.DrinkFlavors
import com.enzoftware.guajolotas.domain.models.FoodFlavors
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.Product.Drink
import com.enzoftware.guajolotas.domain.models.Product.Food

object FakeProducts {
    val drinks = listOf<Product>(
        Drink("Coffee", 12.0, flavor = DrinkFlavors.Coffee),
        Drink("Milk", 22.0, flavor = DrinkFlavors.Milk),
        Drink("Chocolate", 22.0, flavor = DrinkFlavors.Chocolate),
    )

    val tamales = listOf<Product>(
        Food("Tamal Verde", price = 14.0, flavor = FoodFlavors.Green),
        Food("Tamal de Piña", price = 14.0, flavor = FoodFlavors.Pineapple)
    )

    val guajolotas = listOf<Product>(
        Food("Verde", 25.0, flavor = FoodFlavors.Green),
        Food("Piña", 25.0, flavor = FoodFlavors.Pineapple),
        Food("Pasas", 25.0, flavor = FoodFlavors.Raisins),
    )
}
