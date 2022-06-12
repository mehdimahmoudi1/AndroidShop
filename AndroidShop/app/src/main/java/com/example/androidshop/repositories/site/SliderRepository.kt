package com.example.androidshop.repositories.site

import com.example.androidshop.api.site.SliderApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SliderRepository @Inject constructor(private val api:SliderApi) {

    suspend fun getSlider():ServiceResponse<Slider>{
        return try {
            api.getSlider()
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getSliderById(id:Long):ServiceResponse<Slider>{
        return try {
            api.getSliderById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}