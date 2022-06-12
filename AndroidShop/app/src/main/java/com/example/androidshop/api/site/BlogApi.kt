package com.example.androidshop.api.site

import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.site.Blog
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {
    @GET("/api/blog")
    suspend fun getBlog(): ServiceResponse<Blog>

    @GET("/api/blog/{id}")
    suspend fun getBlogById(@Path("id") id : Long): ServiceResponse<Blog>
}