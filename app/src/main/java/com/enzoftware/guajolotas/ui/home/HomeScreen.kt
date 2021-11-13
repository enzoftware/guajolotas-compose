package com.enzoftware.guajolotas.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
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



    GuajolotasTheme {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {
            AppBar(onClickShoppingCart = onClickCart)
            Spacer(modifier = Modifier.height(32.dp))
            HomeHeader()
            Spacer(modifier = Modifier.height(32.dp))
            SearchBar(onClickSearch)
            Spacer(modifier = Modifier.height(32.dp))
            TabBar(tabs = tabs)
            TabsContent(onClick = onClickProduct)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
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
