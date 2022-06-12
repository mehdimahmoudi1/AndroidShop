package com.example.androidshop.repositories.site

import com.example.androidshop.api.site.ContentApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.site.Content
import com.example.androidshop.models.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ContentRepository @Inject constructor(private val api:ContentApi) {
    suspend fun getContent():ServiceResponse<Content>{
        return try {
            api.getContent()
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getContentByTitle(title:String):ServiceResponse<Content>{
        return try {
            api.getContentByTitle(title)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}