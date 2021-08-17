package com.enzoftware.guajolotas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.enzoftware.guajolotas.ui.detail.DetailScreen
import com.enzoftware.guajolotas.ui.home.HomeScreen
import com.enzoftware.guajolotas.ui.search.SearchScreen
import com.enzoftware.guajolotas.ui.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreens.SPLASH) {
        composable(route = NavigationScreens.SPLASH) { SplashScreen(navController = navController) }
        composable(route = NavigationScreens.HOME) { HomeScreen(navController = navController) }
        composable(route = NavigationScreens.SEARCH) { SearchScreen(navController = navController) }
        composable(route = NavigationScreens.DETAIL) { DetailScreen(navController = navController) }
    }
}

object NavigationScreens {
    const val SPLASH = "splash_screen"
    const val HOME = "home_screen"
    const val SEARCH = "search_screen"
    const val DETAIL = "detail_screen"
}
