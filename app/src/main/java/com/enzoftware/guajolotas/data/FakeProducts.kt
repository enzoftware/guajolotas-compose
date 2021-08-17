package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.DrinkFlavors
import com.enzoftware.guajolotas.domain.models.FoodFlavors
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.Product.Drink
import com.enzoftware.guajolotas.domain.models.Product.Food

// TODO: Turn into resource strings
object FakeProducts {
    val drinks = listOf<Product>(
        Drink("Coffee", 12.0, flavor = DrinkFlavors.Coffee, image = R.drawable.coffee),
        Drink("Milk", 22.0, flavor = DrinkFlavors.Milk, image = R.drawable.milk),
        Drink("Chocolate", 22.0, flavor = DrinkFlavors.Chocolate, image = R.drawable.chocolate),
        Drink("Champurrado", 22.0, flavor = DrinkFlavors.Mix, image = R.drawable.champurrado),
    )

    val tamales = listOf<Product>(
        Food("Tamal Verde", price = 18.0, flavor = FoodFlavors.Green, image = R.drawable.green),
        Food(
            "Tamal Piña",
            price = 14.0,
            flavor = FoodFlavors.Pineapple,
            image = R.drawable.pineapple
        ),
        Food("Tamal Mole", price = 16.0, flavor = FoodFlavors.Mole, image = R.drawable.mole),
        Food(
            "Tamal Guayaba",
            price = 20.0,
            flavor = FoodFlavors.Guayaba,
            image = R.drawable.guayaba
        ),
        Food("Tamal Pasas", price = 10.0, flavor = FoodFlavors.Raisins, image = R.drawable.raisins),
    )

    val guajolotas = listOf<Product>(
        Food("Verde", 25.0, flavor = FoodFlavors.Green, image = R.drawable.gua_green),
        Food("Piña", 27.0, flavor = FoodFlavors.Pineapple, image = R.drawable.gua_pineapple),
        Food("Pasas", 29.0, flavor = FoodFlavors.Raisins, image = R.drawable.gua_raisins),
        Food("Mole", 24.0, flavor = FoodFlavors.Mole, image = R.drawable.gua_mole),
        Food("Guayaba", 22.0, flavor = FoodFlavors.Guayaba, image = R.drawable.gua_guayaba),
    )

    val mockDrink = drinks[0]
    val mockFood = tamales[0]
}
