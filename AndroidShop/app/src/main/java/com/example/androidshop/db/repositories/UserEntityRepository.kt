package com.example.androidshop.db.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.androidshop.db.dao.UserEntityDao
import com.example.androidshop.db.database.AndroidShopDataBase
import com.example.androidshop.db.models.UserEntity

class UserEntityRepository(application: Application) {

    private var userDao: UserEntityDao
    private  var currentUserEntity: LiveData<UserEntity>

    init {
        val database = AndroidShopDataBase.getInstance(application)
        userDao = database.userDao()
        currentUserEntity = userDao.get()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return currentUserEntity
    }

    suspend fun insert(user: UserEntity) {
        deleteAll()
        userDao.insert(user)
    }

    suspend fun update(user: UserEntity) {
        userDao.update(user)
    }

    suspend fun delete(user: UserEntity) {
        userDao.delete(user)
    }

     suspend fun deleteAll() {
        return userDao.deleteAll()
    }
}