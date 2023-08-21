package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.success
import com.enzoftware.guajolotas.data.local.ProductDao
import com.enzoftware.guajolotas.data.local.ProductEntity
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.domain.models.toEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductLocalDataSourceTest {

    lateinit var productDao: ProductDao
    lateinit var localDataSource: ProductLocalDataSource

    var products = mutableListOf<ProductEntity>()

    @Before
    fun setup() {
        productDao = mock {
            on { getShoppingCart() } doReturn flow { products }
        }

        localDataSource = ProductLocalDataSource(productDao)
    }

    @Test
    fun `fetchProducts return a successful list of products`() = runBlockingTest {
        val result = localDataSource.fetchProducts().success()
        assertEquals(result?.size, FakeProducts.allProducts.size)
    }

    @Test
    fun `fetchProductDetail return successful product`() = runBlockingTest {
        val anyId = "prod-1"
        val result = localDataSource.fetchProductDetail(anyId).success()

        assertEquals(result?.id, anyId)
    }

    @Test
    fun `searchProduct return a list of products`() = runBlockingTest {
        val anyName = "tamal"

        val result = localDataSource.searchProduct(anyName).success()

        result?.let {
            assert(it.isNotEmpty())
            assertEquals(it.size, FakeProducts.tamales.size)
        }
    }

    @Test
    fun `addProductToShoppingCart add product entity to the cart`() {
        val product = Product("id-10", "product", 10.0, type = ProductType.Tamal)
        val productEntity = product.toEntity()
        whenever(productDao.addProduct(any())).doAnswer {
            Unit.apply {
                products.add(productEntity)
            }
        }

        localDataSource.addProductToShoppingCart(product)

        verify(productDao).addProduct(productEntity)
        assert(products.isNotEmpty())
    }

    @Test
    fun `getShoppingCart returns a Flow instance with products`() = runBlockingTest {
        val product = Product("id-11", "product", 10.0, type = ProductType.Tamal)
        products.add(product.toEntity())

        val result = localDataSource.getShoppingCart()

        verify(productDao).getShoppingCart()
        result.collect {
            assert(it.isNotEmpty())
        }
    }

    @Test
    fun `removeProductFromShoppingCart remove product entity from the cart`() = runBlockingTest {
        val product = Product("id-18", "product", 10.0, type = ProductType.Tamal)
        products.add(product.toEntity())
        whenever(productDao.removeProduct(any())).doAnswer {
            Unit.apply {
                products.remove(product.toEntity())
            }
        }

        val oldProductsSize = products.size
        localDataSource.removeProductFromShoppingCart(product)

        verify(productDao).removeProduct(product.toEntity())
        assertEquals(oldProductsSize - 1, products.size)
    }
}