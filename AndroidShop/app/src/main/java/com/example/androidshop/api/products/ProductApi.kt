package com.example.androidshop.api.products

import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.Product
import com.example.androidshop.models.products.ProductCategory
import com.example.androidshop.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("/api/product")
    suspend fun getProduct(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ServiceResponse<Product>

    @GET("/api/product/cat/{id}")
    suspend fun getProductByCategoryId(
        @Path("id") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ServiceResponse<Product>


    @GET("/api/product/{id}")
    suspend fun getProductById(@Path("id") id: Long): ServiceResponse<Product>

    @GET("/api/product/new")
    suspend fun getNewProduct(): ServiceResponse<Product>

    @GET("/api/product/popular")
    suspend fun getPupularProduct(): ServiceResponse<Product>

}