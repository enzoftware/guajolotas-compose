package com.enzoftware.guajolotas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.enzoftware.guajolotas.ui.home.HomeScreen
import com.enzoftware.guajolotas.ui.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreens.SPLASH) {
        composable(route = NavigationScreens.SPLASH) { SplashScreen(navController = navController) }
        composable(route = NavigationScreens.HOME) { HomeScreen() }
    }
}

object NavigationScreens {
    const val SPLASH = "splash_screen"
    const val HOME = "home_screen"
}
