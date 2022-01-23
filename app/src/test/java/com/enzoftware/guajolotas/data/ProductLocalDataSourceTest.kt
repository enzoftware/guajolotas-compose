package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.data.local.ProductDao
import com.enzoftware.guajolotas.data.local.ProductEntity
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.domain.models.toEntity
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

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
    fun `addProductToShoppingCart add product entity to the cart`() {
        val product = Product("id-10", "product", 10.0, type = ProductType.Tamal)
        val productEntity = product.toEntity()
        whenever(productDao.addProduct(productEntity)).doAnswer {
            Unit.apply {
                products.add(productEntity)
            }
        }

        localDataSource.addProductToShoppingCart(product)

        verify(productDao).addProduct(any())
        assert(products.isNotEmpty())

    }
}