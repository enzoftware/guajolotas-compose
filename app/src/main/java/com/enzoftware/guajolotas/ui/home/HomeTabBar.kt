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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.ErrorScreen
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

data class TabItem(val title: String, val onItemSelected: () -> Unit)

@ExperimentalPagerApi
@Composable
fun TabBar(tabs: List<TabItem>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        backgroundColor = Color.Transparent,
        contentColor = AppColors.primary
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    tab.onItemSelected()
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClick: GoToProductDetail,
    pagerState: PagerState,
    tabs: List<TabItem>
) {
    val state by homeViewModel.state.collectAsState()

    HorizontalPager(
        count = tabs.size,
        state = pagerState,
    ) {
        when (state) {
            is HomeUiModel.Loading -> LoadingScreen()
            is HomeUiModel.ProductsSuccess -> ProductsSuccessFragment(
                products = (state as HomeUiModel.ProductsSuccess).products,
                onClickProduct = onClick,
            )
            is HomeUiModel.Error -> ErrorScreen(exception = (state as HomeUiModel.Error).exception)
        }
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
