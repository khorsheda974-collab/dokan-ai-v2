package com.autobot.wapilot

data class Product(
    val name: String = "",
    val price: String = "",
    val productName: String = "",
    val productPrice: String = ""
) {
    fun getDisplayName(): String {
        return if (productName.isNotEmpty()) productName else name
    }
    fun getDisplayPrice(): String {
        return if (productPrice.isNotEmpty()) productPrice else price
    }
}
