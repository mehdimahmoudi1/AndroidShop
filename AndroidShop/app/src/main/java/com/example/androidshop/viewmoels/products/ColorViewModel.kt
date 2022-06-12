package com.example.androidshop.viewmoels.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.ProductColor
import com.example.androidshop.repositories.products.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val repository: ColorRepository
) : ViewModel() {
    fun getColor(onResponse: (response: ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getColor()
            onResponse(response)
        }
    }
    fun getColorById(id:Long,onResponse: (response: ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getColorById(id)
            onResponse(response)
        }
    }
}