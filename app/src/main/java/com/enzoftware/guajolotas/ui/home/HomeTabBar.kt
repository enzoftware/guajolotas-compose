@file:OptIn(ExperimentalFoundationApi::class)

package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.ErrorScreen
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.theme.AppColors
import kotlinx.coroutines.launch

data class TabItem(val title: String, val onItemSelected: () -> Unit)

@Composable
fun TabBar(tabs: List<TabItem>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
            )
        },
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClick: GoToProductDetail,
    pagerState: PagerState,
    tabs: List<TabItem>
) {
    val state by homeViewModel.state.collectAsState()

    HorizontalPager(
        beyondBoundsPageCount = tabs.size,
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
