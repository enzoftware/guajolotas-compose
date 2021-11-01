package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.core.success
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend fun getProductDetail(id: String): ResultState<ProductDetailModel> {
        val productDetail = productRepository.getProductDetail(id)
        if (productDetail is ResultState.Success) {
            val productsCategory =
                productRepository.fetchProductByType(productDetail.data.type).success()
                    ?: emptyList()
            val selectedProductIndex = productsCategory.indexOf(productDetail.data)
            val type = if (productDetail.data.isFood()) ProductType.Drink else ProductType.Guajolota
            val complements = productRepository.fetchProductByType(type).success() ?: emptyList()
            return ResultState.Success(
                ProductDetailModel(
                    selectedProductIndex,
                    productsCategory,
                    complements
                )
            )
        }
        return ResultState.Error(Exception("Unknown error"))
    }

}

data class ProductDetailModel(
    val selectedProductIndex: Int,
    val productsCategory: List<Product>,
    val complements: List<Product>,
)
