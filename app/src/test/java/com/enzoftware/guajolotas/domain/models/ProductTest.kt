package com.enzoftware.guajolotas.domain.models

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class ProductTest {

    lateinit var product: Product

    @Test
    fun `isDrink returns true for drink product`() {
        product = Product("id-1", "product", 120.0, type = ProductType.Drink)
        assertTrue(product.isDrink())
    }

    @Test
    fun `isTamal returns true for tamal product`() {
        product = Product("id-2", "product", 120.0, type = ProductType.Tamal)
        assertTrue(product.isTamal())
    }

    @Test
    fun `isGuajolota returns true for guajolota product`() {
        product = Product("id-3", "product", 120.0, type = ProductType.Guajolota)
        assertTrue(product.isGuajolota())
    }

    @Test
    fun `isFood returns true for guajolota or tamal product`() {
        product = Product("id-1", "product", 120.0, type = ProductType.Guajolota)
        assertTrue(product.isFood())

        product = Product("id-1", "product", 120.0, type = ProductType.Tamal)
        assertTrue(product.isFood())
    }

    @Test
    fun `increaseQuantity add 1 to product quantity`() {
        product = Product("id-3", "product", 120.0, type = ProductType.Guajolota)

        product.increaseQuantity()
        assertEquals(product.quantity, 1)
    }

    @Test
    fun `decreaseQuantity remove 1 to product quantity`() {
        product = Product("id-3", "product", 120.0, type = ProductType.Guajolota, quantity = 10)

        product.decreaseQuantity()
        assertEquals(product.quantity, 9)
    }


    @Test
    fun `decreaseQuantity not remove 1 to product quantity if is equal to 0`() {
        product = Product("id-3", "product", 120.0, type = ProductType.Guajolota)

        assertEquals(product.quantity, 0)
        product.decreaseQuantity()
        assertEquals(product.quantity, 0)
    }

    @Test
    fun `formattedPrice returns the price of the product as formatted string`() {
        product = Product("id-3", "product", 120.0, type = ProductType.Guajolota)

        assertEquals(product.formattedPrice, "$120.0 PEN")
    }
}