package com.enzoftware.guajolotas.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enzoftware.guajolotas.R
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.ui.components.ComplementCheckBox
import com.enzoftware.guajolotas.ui.components.ErrorScreen
import com.enzoftware.guajolotas.ui.components.GuaButton
import com.enzoftware.guajolotas.ui.components.LoadingScreen
import com.enzoftware.guajolotas.ui.components.ProductCounter
import com.enzoftware.guajolotas.ui.components.ProductFlavor
import com.enzoftware.guajolotas.ui.components.ShoppingCartBadge
import com.enzoftware.guajolotas.ui.theme.AppColors
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun ProductDetailScreen(
    onBackPressed: () -> Unit,
    onClickShoppingCart: () -> Unit,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(true) {
        viewModel.getProductDetail(productId)
    }

    Scaffold(
        topBar = { DetailAppBar(onBackPressed, onClickShoppingCart) }
    ) { padding ->
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

@Composable
private fun DetailAppBar(onBackPressed: () -> Unit, onClickShoppingCart: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios),
            contentDescription = "Go back arrow",
            Modifier.clickable { onBackPressed() })
        ShoppingCartBadge(onClick = onClickShoppingCart)
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun DetailBody(
    products: List<Product>,
    complements: List<Product> = emptyList(),
    initialPage: Int,
) {

    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { products.size })

    val coroutineScope = rememberCoroutineScope()
    val currentProduct = remember { mutableStateOf(products[pagerState.currentPage]) }
    val addedPrice = remember { mutableDoubleStateOf(0.0) }
    val currentQuantity = remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            currentProduct.value = products[it]
            currentQuantity.value = currentProduct.value.quantity
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 48.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            ) {
                val product = products[page]
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
        ProductCounter(decreaseProductCount = {
            currentProduct.value.decreaseQuantity()
            currentQuantity.value = currentProduct.value.quantity
        }, incrementProductCount = {
            currentProduct.value.increaseQuantity()
            currentQuantity.value = currentProduct.value.quantity
        }, count = currentQuantity.value
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
                columns = GridCells.Fixed(3)
            ) {
                items(products.size) { index ->
                    ProductFlavor(
                        image = products[index].flavorImage,
                        onClick = {
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
                columns = GridCells.Fixed(2)
            ) {
                items(complements.size) { index ->
                    ComplementCheckBox(
                        product = complements[index],
                        onClick = { isChecked ->
                            if (isChecked) addedPrice.value += complements[index].price
                            else addedPrice.value -= complements[index].price
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        GuaButton(
            onClick = {
                // TODO: Send products to shopping cart
            },
            modifier = Modifier.height(64.dp),
            content = {
                Row {
                    Text(text = stringResource(R.string.add_to_cart))
                    Spacer(modifier = Modifier.width(48.dp))
                    Text(text = "$${addedPrice.value + (currentProduct.value.price * currentProduct.value.quantity)}")
                }
            },
        )
    }
}


@InternalCoroutinesApi
@ExperimentalFoundationApi
@Preview
@Composable
fun DetailScreenPreview() {
    GuajolotasTheme {
        ProductDetailScreen(onBackPressed = {}, onClickShoppingCart = {}, "2")
    }
}
