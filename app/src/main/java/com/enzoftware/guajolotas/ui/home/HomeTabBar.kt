package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.ErrorScreen
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState

data class TabItem(val title: String, val onItemSelected: () -> Unit)

@ExperimentalPagerApi
@Composable
fun TabBar(tabs: List<TabItem>) {
    val pagerState = rememberPagerState()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = tab.onItemSelected,
                text = { Text(tab.title, fontSize = 16.sp) },
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClick: GoToProductDetail
) {
    val state by homeViewModel.state.collectAsState()

    when (state) {
        is HomeUiModel.Loading -> LoadingScreen()
        is HomeUiModel.ProductsSuccess -> ProductsSuccessFragment(
            products = (state as HomeUiModel.ProductsSuccess).products,
            onClickProduct = onClick,
        )
        is HomeUiModel.Error -> ErrorScreen(exception = (state as HomeUiModel.Error).exception)
    }

}

@Composable
fun ProductsSuccessFragment(
    products: List<Product>,
    onClickProduct: GoToProductDetail
) {
    LazyColumn {
        items(products) { product ->
            ProductItem(
                product = product,
                onClick = { onClickProduct(product.id) },
            )
        }
    }
}
