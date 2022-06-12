package com.example.androidshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidshop.db.models.BasketEntity

@Dao
interface BasketEntityDao {
    @Insert
    suspend fun insert(basketEntity: BasketEntity)

    @Update
    suspend fun update(basketEntity: BasketEntity)

    @Delete
    suspend fun delete(basketEntity: BasketEntity)

    @Query("delete from basketEntity ")
    fun deleteAll()

    @Query("select * from BasketEntity")
    fun getAll(): List<BasketEntity>

    @Query("select * from BasketEntity")
    fun getAllLive(): LiveData<List<BasketEntity>>

}