package com.example.androidshop.api.products

import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorApi {
    @GET("/api/color")
    suspend fun getColors(): ServiceResponse<ProductColor>


    @GET("/api/color/{id}")
    suspend fun getColorById(@Path("id") id : Long): ServiceResponse<ProductColor>
}