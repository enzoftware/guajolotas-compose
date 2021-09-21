package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.Product.Drink
import com.enzoftware.guajolotas.domain.models.Product.Food
import com.enzoftware.guajolotas.domain.models.ProductType

// TODO: Turn into resource strings
object FakeProducts {

    val drinks = listOf<Product>(
            Drink(
                    "Coffee",
                    12.0,
                    image = R.drawable.coffee,
                    flavorImage = R.drawable.flavor_coffee,
                    type = ProductType.Drink,
            ),
            Drink(
                    "Milk",
                    22.0,
                    image = R.drawable.milk,
                    flavorImage = R.drawable.flavor_milk,
                    type = ProductType.Drink,
            ),
            Drink(
                    "Chocolate",
                    22.0,
                    image = R.drawable.chocolate,
                    flavorImage = R.drawable.flavor_chocolate,
                    type = ProductType.Drink,
            ),
            Drink(
                    "Champurrado",
                    22.0,
                    image = R.drawable.champurrado,
                    flavorImage = R.drawable.flavor_champurrado,
                    type = ProductType.Drink,
            ),
    )

    val tamales = listOf<Product>(
            Food(
                    "Tamal Verde",
                    price = 18.0,
                    image = R.drawable.green,
                    flavorImage = R.drawable.flavor_green,
                    type = ProductType.Tamal,
            ),
            Food(
                    "Tamal Piña",
                    price = 14.0,
                    image = R.drawable.pineapple,
                    flavorImage = R.drawable.flavor_pineapple,
                    type = ProductType.Tamal,
            ),
            Food(
                    "Tamal Mole",
                    price = 16.0,
                    image = R.drawable.mole,
                    flavorImage = R.drawable.flavor_mole,
                    type = ProductType.Tamal,
            ),
            Food(
                    "Tamal Guayaba",
                    price = 20.0,
                    image = R.drawable.guayaba,
                    flavorImage = R.drawable.flavor_guayaba,
                    type = ProductType.Tamal,
            ),
            Food(
                    "Tamal Pasas",
                    price = 10.0,
                    image = R.drawable.raisins,
                    flavorImage = R.drawable.flavor_rainsins,
                    type = ProductType.Tamal,
            ),
    )

    val guajolotas = listOf<Product>(
            Food(
                    "Verde",
                    25.0,
                    image = R.drawable.gua_green,
                    flavorImage = R.drawable.flavor_green,
                    type = ProductType.Guajolota,
            ),
            Food(
                    "Piña",
                    27.0,
                    image = R.drawable.gua_pineapple,
                    flavorImage = R.drawable.flavor_pineapple,
                    type = ProductType.Guajolota,
            ),
            Food(
                    "Pasas",
                    29.0,
                    image = R.drawable.gua_raisins,
                    flavorImage = R.drawable.flavor_rainsins,
                    type = ProductType.Guajolota,
            ),
            Food(
                    "Mole",
                    24.0,
                    image = R.drawable.gua_mole,
                    flavorImage = R.drawable.flavor_mole,
                    type = ProductType.Guajolota,
            ),
            Food(
                    "Guayaba",
                    22.0,
                    image = R.drawable.gua_guayaba,
                    flavorImage = R.drawable.flavor_guayaba,
                    type = ProductType.Guajolota,
            ),
    )

    val allProducts = drinks + guajolotas + tamales

    val mockDrink = drinks[0]
    val mockFood = tamales[0]
}
