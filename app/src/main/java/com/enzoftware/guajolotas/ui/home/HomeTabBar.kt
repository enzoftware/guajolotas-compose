package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.enzoftware.guajolotas.data.FakeProducts
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.utils.ComposableFun
import com.google.accompanist.pager.ExperimentalPagerApi

sealed class TabItem(val title: String, val content: ComposableFun) {
    object DrinksTab : TabItem("Bebidas", { DrinkFragment() })
    object TamalesTab : TabItem("TamalesTab", { TamalesFragment() })
    object GuajolotaTab : TabItem("Guajolotas", { GuajolotasFragment() })
}

@Composable
fun DrinkFragment() {
    LazyColumn {
        items(FakeProducts.drinks) { drink ->
            ProductItem(product = drink)
        }
    }
}

@Composable
fun TamalesFragment() {
    LazyColumn {
        items(FakeProducts.tamales) { tamal ->
            ProductItem(product = tamal)
        }
    }
}

@Composable
fun GuajolotasFragment() {
    LazyColumn {
        items(FakeProducts.guajolotas) { guajolota ->
            ProductItem(product = guajolota)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabBar() {

}
