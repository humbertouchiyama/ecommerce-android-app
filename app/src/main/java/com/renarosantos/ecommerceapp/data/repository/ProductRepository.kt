package com.renarosantos.ecommerceapp.data.repository

import com.renarosantos.ecommerceapp.presentation.viewstate.ProductCardViewState

interface ProductRepository {
    suspend fun getProductList() : List<ProductCardViewState>
}