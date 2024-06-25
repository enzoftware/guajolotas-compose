package com.enzoftware.guajolotas.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enzoftware.guajolotas.ui.detail.ProductDetailScreen
import com.enzoftware.guajolotas.ui.home.HomeScreen
import com.enzoftware.guajolotas.ui.navigation.NavigationScreens.PRODUCT_ID
import com.enzoftware.guajolotas.ui.search.SearchScreen
import com.enzoftware.guajolotas.ui.shopping.ShoppingCartScreen
import com.enzoftware.guajolotas.ui.splash.SplashScreen
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreens.SPLASH) {
        composable(route = NavigationScreens.SPLASH) { SplashScreen(navController = navController) }
        composable(route = NavigationScreens.HOME) {
            HomeScreen(
                onClickProduct = { id: String ->
                    navController.navigate(NavigationScreens.DETAIL + "/$id")
                },
                onClickSearch = {
                    navController.navigate(NavigationScreens.SEARCH)
                },
                onClickCart = {
                    navController.navigate(NavigationScreens.SHOPPING_CART)
                }
            )
        }
        composable(route = NavigationScreens.SEARCH) {
            SearchScreen(
                goToProductDetail = { id: String ->
                    navController.navigate(NavigationScreens.DETAIL + "/$id")
                },
                onCancelSearch = { navController.popBackStack() }
            )
        }
        composable(
            route = NavigationScreens.DETAIL + "/{$PRODUCT_ID}",
            arguments = listOf(navArgument(PRODUCT_ID) {
                type = NavType.StringType
            })
        ) {
            ProductDetailScreen(
                onBackPressed = { navController.popBackStack() },
                onClickShoppingCart = { navController.navigate(NavigationScreens.SHOPPING_CART) },
                productId = it.arguments?.getString(PRODUCT_ID) ?: "empty"
            )
        }
        composable(
            route = NavigationScreens.SHOPPING_CART
        ) {
            ShoppingCartScreen()
        }
    }
}

object NavigationScreens {
    const val SPLASH = "splash_screen"
    const val HOME = "home_screen"
    const val SEARCH = "search_screen"
    const val DETAIL = "detail_screen"
    const val PRODUCT_ID = "product_id"
    const val SHOPPING_CART = "shopping_cart"
}
