package com.example.androidshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidshop.db.models.UserEntity

@Dao
interface UserEntityDao {
    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("select * from UserEntity limit 1")
     fun get():LiveData<UserEntity>

    @Query("delete from UserEntity")
     suspend fun deleteAll()
}