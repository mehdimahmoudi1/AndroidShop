package com.example.androidshop.viewmoels.costomers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.cutomers.User
import com.example.androidshop.models.cutomers.UserVM
import com.example.androidshop.models.invoices.Invoice
import com.example.androidshop.models.products.Product
import com.example.androidshop.models.products.ProductCategory
import com.example.androidshop.repositories.customer.UserRepository
import com.example.androidshop.repositories.invoices.InvoiceRepository
import com.example.androidshop.repositories.products.ProductCategoryRepository
import com.example.androidshop.repositories.products.ProductRepository
import com.example.androidshop.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    var token = ThisApp.token

    fun getUserInfo(onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getUserInfo(token)
            onResponse(response)
        }
    }
    fun changePassword(user:UserVM,onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.changePassword(user,token)
            onResponse(response)
        }
    }
    fun loginUser(user:UserVM,onResponse: (response: ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            val response = repository.loginUser(user,token)
            onResponse(response)
        }
    }

    fun registerUser(user:UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.registerUser(user)
            onResponse(response)
        }
    }
    fun update(user:UserVM, onResponse: (response: ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            val response = repository.updateUser(user,token)
            onResponse(response)
        }
    }
}