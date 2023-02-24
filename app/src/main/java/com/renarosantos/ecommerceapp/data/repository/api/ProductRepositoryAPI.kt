package com.renarosantos.ecommerceapp.data.repository.api

import com.renarosantos.ecommerceapp.presentation.viewstate.ProductCardViewState
import com.renarosantos.ecommerceapp.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryAPI @Inject constructor(private val service : ProductService) : ProductRepository {

    override suspend fun getProductList() : List<ProductCardViewState>{
        return withContext(Dispatchers.IO) {
            service.getProductList().map {
                ProductCardViewState(
                    it.title,
                    it.description,
                    "U$ ${it.price}",
                    it.imageUrl
                )
            }
        }
    }
}