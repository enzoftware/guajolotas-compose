package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.data.FakeProducts
import com.enzoftware.guajolotas.ui.ComposableFun
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

sealed class TabItem(val title: String, val content: ComposableFun) {

    data class DrinksTab(val tabTitle: String, val onClick: GoToProductDetail) :
        TabItem(tabTitle, { DrinkFragment(onClick) })

    data class TamalesTab(val tabTitle: String, val onClick: GoToProductDetail) :
        TabItem(tabTitle, { TamalesFragment(onClick) })

    data class GuajolotaTab(val tabTitle: String, val onClick: GoToProductDetail) :
        TabItem(tabTitle, { GuajolotasFragment(onClick) })
}

@Composable
fun DrinkFragment(onClickProduct: GoToProductDetail) {
    LazyColumn {
        items(FakeProducts.drinks) { drink ->
            ProductItem(product = drink, onClick = {
                onClickProduct(drink.name)
            })
        }
    }
}

@Composable
fun TamalesFragment(onClickProduct: GoToProductDetail) {
    LazyColumn {
        items(FakeProducts.tamales) { tamal ->
            ProductItem(product = tamal, onClick = {
                onClickProduct(tamal.name)
            })
        }
    }
}

@Composable
fun GuajolotasFragment(onClickProduct: GoToProductDetail) {
    LazyColumn {
        items(FakeProducts.guajolotas) { guajolota ->
            ProductItem(product = guajolota, onClick = {
                onClickProduct(guajolota.name)
            })
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
        }) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(tab.title, fontSize = 17.sp) },
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
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
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
