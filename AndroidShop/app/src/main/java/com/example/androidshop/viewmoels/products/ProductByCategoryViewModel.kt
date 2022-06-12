package com.example.androidshop.viewmoels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.Product
import com.example.androidshop.models.products.ProductCategory
import com.example.androidshop.repositories.products.ProductCategoryRepository
import com.example.androidshop.repositories.products.ProductRepository
import com.example.androidshop.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductByCategoryViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    var categoryId : Long = ThisApp.categoryId
    var pageSize : Int = 3
    var pageIdex = mutableStateOf(0)
    var scrollPosition = 0
    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getProductByCategoryId(categoryId,pageIdex.value,pageSize) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }

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
            onResponse(response)
        }
    }

    private fun incrementPage(){
        pageIdex.value = pageIdex.value +1
    }

    fun onScrollPositionChange(position:Int){
        scrollPosition = position
    }

    private fun appendItemd(items:List<Product>){
        val current = ArrayList(dataList.value)
        current.addAll(items)
        dataList.value = current
    }
    fun nextPage(){
        viewModelScope.launch {
            if ((scrollPosition +1) >= (pageIdex.value * pageSize) ){
                isLoading.value = true
                incrementPage()
                if (pageIdex.value > 0){
                    getProductByCategoryId(categoryId,pageIdex.value,pageSize){response->
                        if (response.status == "OK" && response.data!!.isNotEmpty()){
                            appendItemd(response.data!!)
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}