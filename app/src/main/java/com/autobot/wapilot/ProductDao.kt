package com.autobot.wapilot
import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<Product>
    
    @Insert
    fun insert(product: Product)
    
    @Update
    fun update(product: Product)
    
    @Delete
    fun delete(product: Product)
    
    @Query("SELECT * FROM products WHERE colourName LIKE :colour LIMIT 10")
    fun searchByColour(colour: String): List<Product>
}
