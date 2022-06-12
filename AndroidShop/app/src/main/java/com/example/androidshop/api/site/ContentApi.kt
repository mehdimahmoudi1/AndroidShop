package com.example.androidshop.api.site

import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.site.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {
    @GET("/api/content")
    suspend fun getContent(): ServiceResponse<Content>

    @GET("/api/content/{title}")
    suspend fun getContentByTitle(@Path("title") title : String): ServiceResponse<Content>
}