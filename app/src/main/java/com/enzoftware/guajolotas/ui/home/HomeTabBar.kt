package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.ComposableFun
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

sealed class TabItem(val title: String, val content: ComposableFun) {

    data class DrinksTab(val tabTitle: String, val onClick: GoToProductDetail) :
        TabItem(tabTitle, { DrinkFragment(onClickProduct = onClick) })

    data class TamalesTab(val tabTitle: String, val onClick: GoToProductDetail) :
        TabItem(tabTitle, { TamalesFragment(onClickProduct = onClick) })

    data class GuajolotaTab(val tabTitle: String, val onClick: GoToProductDetail) :
        TabItem(tabTitle, { GuajolotasFragment(onClickProduct = onClick) })
}

@Composable
fun DrinkFragment(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickProduct: GoToProductDetail
) {
    val state by viewModel.state.collectAsState()

    when {
        state.loading -> LoadingScreen()
        state.products != null -> ProductsSuccessFragment(state.products!!, onClickProduct)
    }
}

@Composable
fun TamalesFragment(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickProduct: GoToProductDetail
) {
    val state by viewModel.state.collectAsState()

    when {
        state.loading -> LoadingScreen()
        state.products != null -> ProductsSuccessFragment(state.products!!, onClickProduct)
    }
}

@Composable
fun GuajolotasFragment(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickProduct: GoToProductDetail
) {
    val state by viewModel.state.collectAsState()

    when {
        state.loading -> LoadingScreen()
        state.products != null -> ProductsSuccessFragment(state.products!!, onClickProduct)
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
    tabs: List<TabItem>,
    pagerState: PagerState,
) {
    HorizontalPager(
        state = pagerState
    ) { page ->
        tabs[page].content()
    }
}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(
        TabItem.DrinksTab(onClick = {}, tabTitle = ""),
        TabItem.TamalesTab(onClick = {}, tabTitle = ""),
        TabItem.GuajolotaTab(onClick = {}, tabTitle = "")
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)

    TabBar(tabs = tabs, pagerState = pagerState)
}
