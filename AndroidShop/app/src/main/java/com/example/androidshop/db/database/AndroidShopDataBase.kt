package com.example.androidshop.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidshop.db.dao.BasketEntityDao
import com.example.androidshop.db.dao.UserEntityDao
import com.example.androidshop.db.models.BasketEntity
import com.example.androidshop.db.models.UserEntity

@Database(entities = [UserEntity::class,BasketEntity::class], version = 5)
abstract class AndroidShopDataBase:RoomDatabase() {

    abstract fun userDao():UserEntityDao
    abstract fun basketDao(): BasketEntityDao
    companion object{
        private var instance : AndroidShopDataBase? = null

        fun getInstance(context: Context):AndroidShopDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AndroidShopDataBase::class.java,
                    "mytododb"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as AndroidShopDataBase
        }
    }
}