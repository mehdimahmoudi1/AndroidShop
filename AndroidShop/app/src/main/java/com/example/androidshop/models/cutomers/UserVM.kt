package com.example.androidshop.models.cutomers

import com.example.androidshop.db.models.UserEntity

data class UserVM(
    var address: String? = "",
    var customerId: Long? = null,
    var firstName: String? = "",
    var id: Long? = null,
    var lastName: String? = "",
    var oldPassword: String? = null,
    var password: String? = null ,
    var phone: String? = "",
    var postalCode: String? = "",
    var reapedPassword: String? = null,
    var token: String? = null,
    var username: String?
){
    fun converToEntity(): UserEntity {
        return UserEntity(
            userId = id!!,
            address = address,
            phone = phone,
            firstName = firstName,
            lastName = lastName,
            postalCode = postalCode,
            username = username,
            token = token,
            customerId = customerId!!
        )
    }
}
