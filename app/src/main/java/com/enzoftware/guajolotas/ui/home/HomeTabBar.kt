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
import com.enzoftware.guajolotas.ui.components.ProductItem
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.enzoftware.guajolotas.ui.utils.ComposableFun
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

sealed class TabItem(val title: String, val content: ComposableFun) {
    object DrinksTab : TabItem("Bebidas", { DrinkFragment() })
    object TamalesTab : TabItem("Tamales", { TamalesFragment() })
    object GuajolotaTab : TabItem("Guajolotas", { GuajolotasFragment() })
}

@Composable
fun DrinkFragment() {
    LazyColumn {
        items(FakeProducts.drinks) { drink ->
            ProductItem(product = drink)
        }
    }
}

@Composable
fun TamalesFragment() {
    LazyColumn {
        items(FakeProducts.tamales) { tamal ->
            ProductItem(product = tamal)
        }
    }
}

@Composable
fun GuajolotasFragment() {
    LazyColumn {
        items(FakeProducts.guajolotas) { guajolota ->
            ProductItem(product = guajolota)
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
        TabItem.DrinksTab,
        TabItem.TamalesTab,
        TabItem.GuajolotaTab
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)
    TabBar(tabs = tabs, pagerState = pagerState)
}
