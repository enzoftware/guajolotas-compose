package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.ui.GoToProductDetail
import com.enzoftware.guajolotas.ui.components.GuajolotaLogo
import com.enzoftware.guajolotas.ui.components.ShoppingCartBadge
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onClickProduct: GoToProductDetail,
    onClickSearch: () -> Unit,
    onClickCart: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val tabs = listOf(
        TabItem(stringResource(R.string.guajolotas)) { viewModel.getGuajolotas() },
        TabItem(stringResource(R.string.drinks)) { viewModel.getDrinks() },
        TabItem(stringResource(R.string.tamales)) { viewModel.getTamales() }
    )

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scrollState = rememberScrollState()

    GuajolotasTheme {
        Surface(modifier = Modifier.fillMaxHeight()) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                AppBar(onClickShoppingCart = onClickCart)
                Spacer(modifier = Modifier.height(32.dp))
                HomeHeader()
                Spacer(modifier = Modifier.height(32.dp))
                SearchBar(onClickSearch)
                Spacer(modifier = Modifier.height(32.dp))
                TabBar(tabs = tabs, pagerState = pagerState)
                TabsContent(onClick = onClickProduct, tabs = tabs, pagerState = pagerState)
            }
        }
    }
}


@Composable
private fun AppBar(onClickShoppingCart: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GuajolotaLogo(modifier = Modifier.size(64.dp))
        ShoppingCartBadge(onClick = onClickShoppingCart)
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
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search icon"
            )
            Text(text = "Sabor de Guajolota, bebida...")
        }
    }
}
