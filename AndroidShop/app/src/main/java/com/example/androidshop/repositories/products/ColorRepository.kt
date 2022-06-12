package com.example.androidshop.repositories.products

import com.example.androidshop.api.products.ColorApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val api: ColorApi) {
    suspend fun getColor():ServiceResponse<ProductColor>{
        return try {
            api.getColors()
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getColorById(id:Long):ServiceResponse<ProductColor>{
        return try {
            api.getColorById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}