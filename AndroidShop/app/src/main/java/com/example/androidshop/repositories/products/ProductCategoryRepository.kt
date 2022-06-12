package com.example.androidshop.repositories.products

import com.example.androidshop.api.products.ProductCategoryApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) {
    suspend fun getProductCategory():ServiceResponse<ProductCategory>{
        return try {
            api.getCategory()
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getProductCategoryById(id:Long):ServiceResponse<ProductCategory>{
        return try {
            api.getCategoryById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}