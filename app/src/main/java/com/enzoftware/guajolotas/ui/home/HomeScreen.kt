package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.GuajolotaLogo
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@ExperimentalPagerApi
@Composable
fun HomeScreen(onClickProduct: GoToProductDetail, onClickSearch: () -> Unit) {
    val tabs = listOf(
        TabItem.GuajolotaTab(onClickProduct),
        TabItem.DrinksTab(onClickProduct),
        TabItem.TamalesTab(onClickProduct)
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)
    GuajolotasTheme {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {
            AppBar()
            Spacer(modifier = Modifier.height(32.dp))
            HomeHeader()
            Spacer(modifier = Modifier.height(32.dp))
            SearchBar(onClickSearch)
            Spacer(modifier = Modifier.height(32.dp))
            TabBar(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@Composable
fun AppBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GuajolotaLogo(modifier = Modifier.size(64.dp))
        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "Appbar"
        )
    }
}

@Composable
fun HomeHeader() {
    Text(
        text = "Nada como una Guajolota para empezar el día",
        fontSize = 34.sp,
        fontWeight = FontWeight.W700
    )
}

@Composable
fun SearchBar(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color(0XFFE7E7E7))
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search icon"
            )
            Text(text = "Sabor de guajolota, bebida...")
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    GuajolotasTheme {
        HomeScreen(onClickProduct = {}, onClickSearch = {})
    }
}
