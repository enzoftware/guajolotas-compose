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
        Drink(
            "Coffee",
            12.0,
            flavor = DrinkFlavors.Coffee,
            image = R.drawable.coffee,
            flavorImage = R.drawable.flavor_coffee,
        ),
        Drink(
            "Milk",
            22.0,
            flavor = DrinkFlavors.Milk,
            image = R.drawable.milk,
            flavorImage = R.drawable.flavor_milk,
        ),
        Drink(
            "Chocolate",
            22.0,
            flavor = DrinkFlavors.Chocolate,
            image = R.drawable.chocolate,
            flavorImage = R.drawable.flavor_chocolate
        ),
        Drink(
            "Champurrado",
            22.0,
            flavor = DrinkFlavors.Mix,
            image = R.drawable.champurrado,
            flavorImage = R.drawable.flavor_champurrado,
        ),
    )

    val tamales = listOf<Product>(
        Food(
            "Tamal Verde",
            price = 18.0,
            flavor = FoodFlavors.Green,
            image = R.drawable.green,
            flavorImage = R.drawable.flavor_green,
        ),
        Food(
            "Tamal Piña",
            price = 14.0,
            flavor = FoodFlavors.Pineapple,
            image = R.drawable.pineapple,
            flavorImage = R.drawable.flavor_pineapple
        ),
        Food(
            "Tamal Mole",
            price = 16.0,
            flavor = FoodFlavors.Mole,
            image = R.drawable.mole,
            flavorImage = R.drawable.flavor_mole
        ),
        Food(
            "Tamal Guayaba",
            price = 20.0,
            flavor = FoodFlavors.Guayaba,
            image = R.drawable.guayaba,
            flavorImage = R.drawable.flavor_guayaba
        ),
        Food(
            "Tamal Pasas",
            price = 10.0,
            flavor = FoodFlavors.Raisins,
            image = R.drawable.raisins,
            flavorImage = R.drawable.flavor_rainsins
        ),
    )

    val guajolotas = listOf<Product>(
        Food(
            "Verde",
            25.0,
            flavor = FoodFlavors.Green,
            image = R.drawable.gua_green,
            flavorImage = R.drawable.flavor_green,
        ),
        Food(
            "Piña",
            27.0,
            flavor = FoodFlavors.Pineapple,
            image = R.drawable.gua_pineapple,
            flavorImage = R.drawable.flavor_pineapple,
        ),
        Food(
            "Pasas",
            29.0,
            flavor = FoodFlavors.Raisins,
            image = R.drawable.gua_raisins,
            flavorImage = R.drawable.flavor_rainsins,
        ),
        Food(
            "Mole",
            24.0,
            flavor = FoodFlavors.Mole,
            image = R.drawable.gua_mole,
            flavorImage = R.drawable.flavor_mole,
        ),
        Food(
            "Guayaba",
            22.0,
            flavor = FoodFlavors.Guayaba,
            image = R.drawable.gua_guayaba,
            flavorImage = R.drawable.flavor_guayaba,
        ),
    )

    val mockDrink = drinks[0]
    val mockFood = tamales[0]
}
