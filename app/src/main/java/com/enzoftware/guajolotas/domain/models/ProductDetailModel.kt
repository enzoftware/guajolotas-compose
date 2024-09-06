package com.enzoftware.guajolotas.domain.models


/**
 * Data class representing the details of a product selected.
 *
 * @property selectedProductIndex The index of the currently selected product within the [productsCategory] list.
 * @property productsCategory The list of products in the same category as the selected product.
 * @property complements A list of complementary products that can be purchased along with the selected product.
 */
data class ProductDetailModel(
    val selectedProductIndex: Int,
    val productsCategory: List<Product>,
    val complements: List<Product>,
)