package com.autobot.wapilot
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productName: String,
    val productPrice: String,
    val productDetails: String,
    val colourName: String,
    val colourHex: String,
    val stock: Int,
    val imageUri: String,
    val category: String // dress, bag, juta, pant
)
