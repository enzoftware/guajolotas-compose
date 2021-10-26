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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

data class TabItem(val title: String, val onItemSelected: () -> Unit)

@ExperimentalPagerApi
@Composable
fun TabBar(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = colorResource(id = android.R.color.transparent),
        contentColor = AppColors.primary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title, fontSize = 16.sp) },
                selected = pagerState.currentPage == index,
                onClick = {
                    tab.onItemSelected()
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
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

    when {
        state.loading -> LoadingScreen()
        state.products != null -> ProductsSuccessFragment(
            products = state.products!!,
            onClickProduct = onClick,
        )
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
