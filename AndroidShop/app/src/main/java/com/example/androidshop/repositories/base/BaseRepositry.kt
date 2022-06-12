package com.example.androidshop.repositories.base

import java.util.*

open class BaseRepositry {
    protected fun preperToken(token: String): String {
        var fixedToken = token
        if (!fixedToken.lowercase(Locale.getDefault()).startsWith("bearer")) {
            fixedToken = "Bearer $token"
        }
        return fixedToken
    }
}