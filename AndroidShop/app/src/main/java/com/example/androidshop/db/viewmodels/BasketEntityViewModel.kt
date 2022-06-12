package com.example.androidshop.db.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.androidshop.db.models.BasketEntity
import com.example.androidshop.db.repositories.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = BasketEntityRepository(application)
    var dataList = mutableStateOf<List<BasketEntity>>(listOf())

    fun getBasketListLive(): LiveData<List<BasketEntity>> {
        return repository.getAllBasketLive()
    }

    private suspend fun insert(basketEntity: BasketEntity) {
        repository.insert(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.delete(basketEntity)
    }
    suspend fun incrementQuantity(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.incrementQuantity(basketEntity)
    }
    suspend fun decrementQuantity(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.decrementQuantity(basketEntity)
    }

    private suspend fun update(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.update(basketEntity)
    }

    suspend fun addToBasket(basketEntity: BasketEntity) {
        if (dataList.value.any { x ->
                x.productId == basketEntity.productId &&
                        x.quantity == basketEntity.quantity && x.colerId == basketEntity.colerId &&
                        x.sizeId == basketEntity.sizeId

            }) {
            val oldBasket = dataList.value.first { x ->
                x.productId == basketEntity.productId
            }
            oldBasket.quantity ++
            oldBasket.price = basketEntity.price * oldBasket.quantity
            update(oldBasket)
        }else{
            insert(basketEntity)
        }
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }
}