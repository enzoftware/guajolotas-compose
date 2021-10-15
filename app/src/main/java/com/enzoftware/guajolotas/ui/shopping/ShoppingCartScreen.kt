package com.enzoftware.guajolotas.ui.shopping

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import kotlinx.coroutines.flow.collect

@Composable
fun ShoppingCartScreen(shoppingCartViewModel: ShoppingCartViewModel = hiltViewModel()) {

    val state by shoppingCartViewModel.state.collectAsState()

    Scaffold {
        when {
            state.loading -> LoadingScreen()
            state.products != null -> Column {

            }
        }
    }
}
