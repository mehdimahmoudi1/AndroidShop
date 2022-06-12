package com.example.androidshop.repositories.customer

import com.example.androidshop.api.costomers.UserApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.cutomers.User
import com.example.androidshop.models.cutomers.UserVM
import com.example.androidshop.repositories.base.BaseRepositry
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val api: UserApi): BaseRepositry() {

    suspend fun getUserInfo(token: String): ServiceResponse<User> {
        preperToken(token)
        return try {
            api.getUserInfo(preperToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun changePassword(data: UserVM, token: String): ServiceResponse<User> {
        return try {
            api.changePassword(data, preperToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun registerUser(data: UserVM): ServiceResponse<User> {
        return try {
            api.registerUser(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun loginUser(data: UserVM, token: String): ServiceResponse<UserVM> {
        return try {
            api.loginUser(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun updateUser(data: UserVM, token: String): ServiceResponse<UserVM> {
        return try {
            api.editUser(data, preperToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

}