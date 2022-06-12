package com.example.androidshop.viewmoels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.site.Content
import com.example.androidshop.repositories.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val repository: ContentRepository
) : ViewModel() {
    fun getContent(onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getContent()
            onResponse(response)
        }
    }
    fun getContentByTitle(title:String,onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getContentByTitle(title)
            onResponse(response)
        }
    }
}