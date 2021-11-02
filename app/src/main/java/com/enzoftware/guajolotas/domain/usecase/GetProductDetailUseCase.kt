package com.enzoftware.guajolotas.domain.usecase

import com.enzoftware.guajolotas.core.ResultState
import com.enzoftware.guajolotas.core.success
import com.enzoftware.guajolotas.domain.models.Product
import com.enzoftware.guajolotas.domain.models.ProductType
import com.enzoftware.guajolotas.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend fun getProductDetail(id: String): ResultState<ProductDetailModel> {
        val detail = repository.getProductDetail(id)
        if (detail is ResultState.Success) {
            val productsCategory = repository.fetchProductByType(detail.data.type).success()
                ?: emptyList()
            val selectedProductIndex = productsCategory.indexOf(detail.data)
            val type = if (detail.data.isFood()) ProductType.Drink else ProductType.Guajolota
            val complements = repository.fetchProductByType(type).success() ?: emptyList()
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
