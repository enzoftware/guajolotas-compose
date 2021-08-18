package com.enzoftware.guajolotas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.enzoftware.guajolotas.ui.detail.DetailScreen
import com.enzoftware.guajolotas.ui.home.HomeScreen
import com.enzoftware.guajolotas.ui.navigation.NavigationScreens.PRODUCT_ID
import com.enzoftware.guajolotas.ui.search.SearchScreen
import com.enzoftware.guajolotas.ui.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
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
            )
        }
        composable(route = NavigationScreens.SEARCH) { SearchScreen(navController = navController) }
        composable(
            route = NavigationScreens.DETAIL + "/{$PRODUCT_ID}",
            arguments = listOf(navArgument(PRODUCT_ID) {
                type = NavType.StringType
            })
        ) {
            DetailScreen(navController = navController)
        }
    }
}

object NavigationScreens {
    const val SPLASH = "splash_screen"
    const val HOME = "home_screen"
    const val SEARCH = "search_screen"
    const val DETAIL = "detail_screen"
    const val PRODUCT_ID = "product_id"
}
