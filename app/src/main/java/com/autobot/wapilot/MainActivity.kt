package com.autobot.wapilot
import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this)

        val products = listOf(
            Product("Chal 1kg", "70 Tk"),
            Product("Dal 1kg", "120 Tk"),
            Product("Tel 1L", "180 Tk"),
            Product("Chini 1kg", "90 Tk")
        )
        
        rv.adapter = ProductAdapter(products)
    }
}
