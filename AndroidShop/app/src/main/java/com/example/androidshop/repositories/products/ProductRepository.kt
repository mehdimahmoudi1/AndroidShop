package com.example.androidshop.repositories.products

import com.example.androidshop.api.products.ProductApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.products.Product
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductRepository @Inject constructor(private val api: ProductApi) {

    suspend fun getProduct(pageIndex:Int, pageSize:Int): ServiceResponse<Product> {
        return try {
            api.getProduct(pageIndex,pageSize)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getProductByCategoryId(categoryId:Long,pageIndex:Int, pageSize:Int): ServiceResponse<Product> {
        return try {
            api.getProductByCategoryId(categoryId,pageIndex,pageSize)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getPopularProduct(): ServiceResponse<Product> {
        return try {
            api.getPupularProduct()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getNewProduct(): ServiceResponse<Product> {
        return try {
            api.getNewProduct()
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun getProductById(id: Long): ServiceResponse<Product> {
        return try {
            api.getProductById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}