package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType

// TODO: Turn into resource strings
object FakeProducts {

    val drinks = listOf(
        Product(
            "prod-1",
            "Coffee",
            12.0,
            image = R.drawable.coffee,
            flavorImage = R.drawable.flavor_coffee,
            type = ProductType.Drink,
        ),
        Product(
            "prod-2",
            "Milk",
            22.0,
            image = R.drawable.milk,
            flavorImage = R.drawable.flavor_milk,
            type = ProductType.Drink,
        ),
        Product(
            "prod-3",
            "Chocolate",
            22.0,
            image = R.drawable.chocolate,
            flavorImage = R.drawable.flavor_chocolate,
            type = ProductType.Drink,
        ),
        Product(
            "prod-4",
            "Champurrado",
            22.0,
            image = R.drawable.champurrado,
            flavorImage = R.drawable.flavor_champurrado,
            type = ProductType.Drink,
        ),
    )

    val tamales = listOf(
        Product(
            "prod-5",
            "Tamal Verde",
            price = 18.0,
            image = R.drawable.green,
            flavorImage = R.drawable.flavor_green,
            type = ProductType.Tamal,
        ),
        Product(
            "prod-6",
            "Tamal Piña",
            price = 14.0,
            image = R.drawable.pineapple,
            flavorImage = R.drawable.flavor_pineapple,
            type = ProductType.Tamal,
        ),
        Product(
            "prod-7",
            "Tamal Mole",
            price = 16.0,
            image = R.drawable.mole,
            flavorImage = R.drawable.flavor_mole,
            type = ProductType.Tamal,
        ),
        Product(
            "prod-8",
            "Tamal Guayaba",
            price = 20.0,
            image = R.drawable.guayaba,
            flavorImage = R.drawable.flavor_guayaba,
            type = ProductType.Tamal,
        ),
        Product(
            "prod-9",
            "Tamal Pasas",
            price = 10.0,
            image = R.drawable.raisins,
            flavorImage = R.drawable.flavor_rainsins,
            type = ProductType.Tamal,
        ),
    )

    val guajolotas = listOf(
        Product(
            "prod-10",
            "Verde",
            25.0,
            image = R.drawable.gua_green,
            flavorImage = R.drawable.flavor_green,
            type = ProductType.Guajolota,
        ),
        Product(
            "prod-11",
            "Piña",
            27.0,
            image = R.drawable.gua_pineapple,
            flavorImage = R.drawable.flavor_pineapple,
            type = ProductType.Guajolota,
        ),
        Product(
            "prod-12",
            "Pasas",
            29.0,
            image = R.drawable.gua_raisins,
            flavorImage = R.drawable.flavor_rainsins,
            type = ProductType.Guajolota,
        ),
        Product(
            "prod-13",
            "Mole",
            24.0,
            image = R.drawable.gua_mole,
            flavorImage = R.drawable.flavor_mole,
            type = ProductType.Guajolota,
        ),
        Product(
            "prod-14",
            "Guayaba",
            22.0,
            image = R.drawable.gua_guayaba,
            flavorImage = R.drawable.flavor_guayaba,
            type = ProductType.Guajolota,
        ),
    )

    val allProducts = drinks + guajolotas + tamales
}
