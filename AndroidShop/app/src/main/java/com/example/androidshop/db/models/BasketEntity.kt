package com.example.androidshop.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var productId: Long,
    var quantity: Int,
    var colerId: Long,
    var sizeId: Long,
    var image: String,
    var price: Long,
    var title: String,
    var colorHex : String,
    var size : String
)
