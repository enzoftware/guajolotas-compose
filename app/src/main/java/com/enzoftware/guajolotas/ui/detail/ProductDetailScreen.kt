package com.enzoftware.guajolotas.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.components.*
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun ProductDetailScreen(
    onBackPressed: () -> Unit,
    onClickShoppingCart: () -> Unit,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    viewModel.getProductDetail(productId)

    Scaffold(topBar = { DetailAppBar(onBackPressed, onClickShoppingCart) }) {
        when (state) {
            is ProductDetailUiModel.Loading -> LoadingScreen()
            is ProductDetailUiModel.ProductDetail -> {
                val productState = state as ProductDetailUiModel.ProductDetail

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    item {
                        DetailBody(
                            products = productState.productDetailModel.productsCategory,
                            complements = productState.productDetailModel.complements,
                            initialPage = productState.productDetailModel.selectedProductIndex
                        )
                    }
                }
            }
            is ProductDetailUiModel.Error -> ErrorScreen(exception = (state as ProductDetailUiModel.Error).exception)
        }
    }

}


@ExperimentalMaterialApi
@Composable
private fun DetailAppBar(onBackPressed: () -> Unit, onClickShoppingCart: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios),
            contentDescription = "Go back arrow",
            Modifier.clickable { onBackPressed() }
        )
        ShoppingCartBadge(onClick = onClickShoppingCart)
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun DetailBody(
    products: List<Product>,
    complements: List<Product> = emptyList(),
    initialPage: Int,
) {

    val pagerState = rememberPagerState(initialPage = initialPage)

    val coroutineScope = rememberCoroutineScope()
    val currentProduct = remember { mutableStateOf(products[pagerState.currentPage]) }
    val addedPrice = remember { mutableStateOf(0.0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            currentProduct.value = products[it]
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            count = products.size,
            contentPadding = PaddingValues(horizontal = 48.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val product = products[page]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .offset {
                        // Calculate the offset for the current page from the
                        // scroll position
                        val pageOffset =
                            this@HorizontalPager.calculateCurrentOffsetForPage(page)
                        // Then use it as a multiplier to apply an offset
                        IntOffset(
                            x = (36.dp * pageOffset).roundToPx(),
                            y = 0
                        )
                    }
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
                currentProduct.value.decreaseQuantity()
            },
            incrementProductCount = {
                currentProduct.value.increaseQuantity()
            },
            count = currentProduct.value.quantity
        )
        Text(
            text = stringResource(R.string.flavor),
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 24.dp)
        )
        Box(
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
        ) {
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
        }
        Text(
            text = stringResource(R.string.guajolocombo),
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp)
        )
        Text(text = stringResource(R.string.detail_screen_choose_complement))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2)
            ) {
                items(complements) { complement ->
                    ComplementCheckBox(
                        product = complement,
                        onClick = { isChecked ->
                            if (isChecked) addedPrice.value += complement.price
                            else addedPrice.value -= complement.price
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        GuaButton(
            onClick = {

            },
            modifier = Modifier.height(64.dp),
            content = {
                Row {
                    Text(text = stringResource(R.string.add_to_cart))
                    Spacer(modifier = Modifier.width(48.dp))
                    Text(text = "$${addedPrice.value + currentProduct.value.price}")
                }
            },
        )
    }
}


@ExperimentalMaterialApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Preview
@Composable
fun DetailScreenPreview() {
    GuajolotasTheme {
        ProductDetailScreen(onBackPressed = {}, onClickShoppingCart = {}, "2")
    }
}
