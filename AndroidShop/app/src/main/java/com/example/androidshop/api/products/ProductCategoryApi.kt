package com.example.androidshop.api.products

import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.ProductCategory
import com.example.androidshop.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductCategoryApi {
    @GET("/api/productCategory")
    suspend fun getCategory(): ServiceResponse<ProductCategory>


    @GET("/api/productCategory/{id}")
    suspend fun getCategoryById(@Path("id") id : Long): ServiceResponse<ProductCategory>
}