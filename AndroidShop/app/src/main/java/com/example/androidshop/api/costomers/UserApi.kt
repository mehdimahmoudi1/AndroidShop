package com.example.androidshop.api.costomers

import com.example.androidshop.models.cutomers.UserVM
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.cutomers.User
import retrofit2.http.*

interface UserApi {

    @GET("/api/user")
    suspend fun getUserInfo(@Header("Authorization") token : String): ServiceResponse<User>


    @PUT("/api/user/changePassword")
    suspend fun changePassword(
        @Body user : UserVM,
        @Header("Authorization") token : String
    ): ServiceResponse<User>

    @POST("/api/user/login")
    suspend fun loginUser(
        @Body user : UserVM
    ): ServiceResponse<UserVM>

    @POST("/api/user/register")
    suspend fun registerUser(
        @Body user : UserVM
    ): ServiceResponse<User>

    @PUT("/api/user/update")
    suspend fun editUser(
        @Body user : UserVM,
        @Header("Authorization") token : String
    ): ServiceResponse<UserVM>

}