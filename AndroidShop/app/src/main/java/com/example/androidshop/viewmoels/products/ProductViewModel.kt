package com.example.androidshop.viewmoels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.Product
import com.example.androidshop.models.products.ProductCategory
import com.example.androidshop.repositories.products.ProductCategoryRepository
import com.example.androidshop.repositories.products.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    var dataList = mutableStateOf<List<Product>>(listOf())
    var data = mutableStateOf<Product?>(null)
    var isLoading = mutableStateOf(true)

    init {
        getProduct(0, 8) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }

        }
    }

    fun getProduct(
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getProduct(pageIndex, pageSize)
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onResponse(response)
        }
    }
    fun getProductByCategoryId(
        categoryId:Long,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getProductByCategoryId(categoryId,pageIndex, pageSize)
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onResponse(response)
        }
    }

    fun getNewProduct(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getNewProduct()
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onResponse(response)
        }
    }

    fun getPopularProduct(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getPopularProduct()
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onResponse(response)
        }
    }

    fun getProductById(id: Long, onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getProductById(id)
            onResponse(response)
        }
    }
}