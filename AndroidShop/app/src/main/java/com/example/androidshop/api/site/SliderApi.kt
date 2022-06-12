package com.example.androidshop.api.site

import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path

interface SliderApi {
    @GET("/api/slider")
    suspend fun getSlider(): ServiceResponse<Slider>

    @GET("/api/slider/{id}")
    suspend fun getSliderById(@Path("id") id : Long): ServiceResponse<Slider>
}