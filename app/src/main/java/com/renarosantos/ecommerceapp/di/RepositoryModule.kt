package com.renarosantos.ecommerceapp.di

import com.renarosantos.ecommerceapp.data.repository.ProductRepository
import com.renarosantos.ecommerceapp.data.repository.api.ApiClient
import com.renarosantos.ecommerceapp.data.repository.api.ProductRepositoryAPI
import com.renarosantos.ecommerceapp.data.repository.api.ProductService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesProductService() : ProductService = ApiClient.getService()

    @Provides
    fun providesProductRepositoryAPI(
        service: ProductService
    ) : ProductRepositoryAPI = ProductRepositoryAPI(service)

    @Provides
    fun providesProductRepository(
        productRepositoryAPI: ProductRepositoryAPI
    ) : ProductRepository = productRepositoryAPI
}