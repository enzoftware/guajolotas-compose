package com.enzoftware.guajolotas.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.data.FakeProducts
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.components.ProductCounter
import com.enzoftware.guajolotas.ui.components.ProductFlavor
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun DetailScreen(onBackPressed: () -> Unit) {
    val products = FakeProducts.tamales
    val pagerState = rememberPagerState(pageCount = products.size, initialOffscreenLimit = 2)
    val scrolState = rememberScrollState()

    Scaffold {
        Column(
            Modifier.padding(24.dp)
        ) {
            DetailAppBar(onBackPressed)
            DetailBody(products = products, pagerState = pagerState)
        }
    }
}

@Composable
private fun DetailAppBar(onBackPressed: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios),
            contentDescription = "Go back arrow",
            Modifier.clickable { onBackPressed() }
        )
        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "Go to shopping cart"
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun DetailBody(
    products: List<Product>,
    complements: List<Product> = emptyList(),
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val currentProduct = products[pagerState.currentPage]
        val currentQuantity = remember {
            mutableStateOf(currentProduct.quantity)
        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            itemSpacing = 8.dp
        ) { index ->
            val product = products[index]
            Column(
                modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "Product image",
                    Modifier.size(160.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = product.name, fontSize = 28.sp, fontWeight = FontWeight.W700)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.formattedPrice,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = AppColors.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProductCounter(
            decreaseProductCount = {
                currentProduct.decreaseQuantity()
                currentQuantity.value -= 1
            },
            incrementProductCount = {
                currentProduct.increaseQuantity()
                currentQuantity.value += 1
            },
            count = products[pagerState.currentPage].quantity
        )
        Text(
            text = stringResource(R.string.flavor),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 24.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)
        ) {
            items(products) { product ->
                ProductFlavor(
                    image = product.flavorImage,
                    onClick = {
                        val index = products.indexOf(product)
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Guajolocombo")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Selecciona la bebida que más te guste y disfruta de tu desayuno.")
    }
}


@ExperimentalFoundationApi
@ExperimentalPagerApi
@Preview
@Composable
fun DetailScreenPreview() {
    GuajolotasTheme {
        DetailScreen(onBackPressed = {})
    }
}
