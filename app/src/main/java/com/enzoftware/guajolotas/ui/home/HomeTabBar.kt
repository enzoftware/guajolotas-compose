package com.enzoftware.guajolotas.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.enzoftware.guajolotas.ui.utils.ComposableFun
import com.google.accompanist.pager.ExperimentalPagerApi

sealed class TabItem(val title: String, val content: ComposableFun) {
    object Drinks : TabItem("Bebidas", { DrinkFragment() })
    object Tamales : TabItem("Tamales", { TamalesFragment() })
    object Guajolota : TabItem("Guajolotas", { GuajolotasFragment() })
}

@Composable
fun DrinkFragment() {
    Text(text = "Bebidas")
}

@Composable
fun TamalesFragment() {
    Text(text = "Tamales")
}

@Composable
fun GuajolotasFragment() {
    Text(text = "Guajolotas")
}

@ExperimentalPagerApi
@Composable
fun TabBar() {

}
