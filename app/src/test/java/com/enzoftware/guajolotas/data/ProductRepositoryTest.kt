package com.enzoftware.guajolotas.data

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.core.success
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.domain.repository.ProductRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class ProductRepositoryTest {

    private lateinit var repository: ProductRepository
    private lateinit var localDataSource: ProductLocalDataSource

    private val products = listOf(
        Product("id-1", "prod-1", 100.0, type = ProductType.Tamal),
        Product("id-2", "prod-2", 110.0, type = ProductType.Drink),
        Product("id-3", "prod-3", 120.0, type = ProductType.Tamal),
        Product("id-4", "prod-4", 150.0, type = ProductType.Guajolota),
    )

    @Before
    fun setup() {
        localDataSource = mock()
        runBlocking {
            whenever(localDataSource.fetchProducts()).doReturn(ResultState.Success(products))
            whenever(localDataSource.fetchProductDetail(any())).doReturn(
                ResultState.Success(
                    products.first()
                )
            )
        }
        repository = ProductRepositoryImpl(localDataSource)
    }

    @Test
    fun `on fetchProducts returns a successful list of products`() = runBlockingTest {
        val result = repository.fetchProducts().success()

        verify(localDataSource).fetchProducts()
        result?.let { assertEquals(it.size, products.size) }
    }

    @Test
    fun `on fetchProductDetail returns a successful product`() = runBlockingTest {
        val anyId = "any-id"
        val result = repository.getProductDetail(anyId).success()

        verify(localDataSource).fetchProductDetail(anyId)
        assertEquals(result?.id, products.first().id)
    }

    @Test
    fun `on fetchProductByType return a successful list of product`() = runBlockingTest {
        val productType = ProductType.Tamal
        val productsByType = products.filter { it.type == productType }
        val result = repository.fetchProductByType(productType).success()

        verify(localDataSource).fetchProducts()
        result?.let { assertEquals(it.size, productsByType.size) }
    }

    @Test
    fun `on searchName return all products occurrences that contains the input string`() =
        runBlockingTest {
            val searchInput = "prod"
            repository.searchProduct(searchInput)

            verify(localDataSource).searchProduct(searchInput)
        }
}

