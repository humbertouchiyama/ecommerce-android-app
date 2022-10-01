package com.renarosantos.ecommerceapp.product_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renarosantos.ecommerceapp.shared.data.repository.ProductRepository
import com.renarosantos.ecommerceapp.wishlist.data.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val wishlistRepository: WishlistRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ProductListViewState>()
    val viewState: LiveData<ProductListViewState>
        get() = _viewState


    fun loadProductList() {
        viewModelScope.launch {
            _viewState.postValue(ProductListViewState.Loading)
            // Data call to fetch products
            val productList = repository.getProductList()
            _viewState.postValue(
                ProductListViewState.Content(
                    productList.map {
                        ProductCardViewState(
                            it.productId,
                            it.title,
                            it.description,
                            "US $ ${it.price}",
                            it.imageUrl,
                            wishlistRepository.isFavorite(it.productId)
                        )
                    }
                ))
        }
    }

    fun onWishListClicked(viewState: ProductCardViewState) {
        viewModelScope.launch {
            if (viewState.isFavorite) {
                wishlistRepository.removeFromWishlist(viewState.id)
            } else {
                wishlistRepository.addToWishlist(
                    viewState.id,
                    viewState.title
                )
            }
            val currentViewState = _viewState.value
            (currentViewState as? ProductListViewState.Content)?.let { content ->
                _viewState.postValue(
                    content.updateFavoriteProduct(
                        viewState.id,
                        !viewState.isFavorite
                    )
                )
            }
        }
    }
}