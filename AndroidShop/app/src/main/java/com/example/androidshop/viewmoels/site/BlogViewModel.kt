package com.example.androidshop.viewmoels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.site.Blog
import com.example.androidshop.repositories.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val repository: BlogRepository
) : ViewModel() {
    fun getBlog(onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getBlog()
            onResponse(response)
        }
    }
    fun getBlogById(id:Long,onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getBlogById(id)
            onResponse(response)
        }
    }
}