package com.autobot.wapilot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val productList = listOf(
            Product(name = "Test Product 1", price = "500 Tk"),
            Product(name = "Test Product 2", price = "800 Tk"),
            Product(name = "Test Product 3", price = "1200 Tk")
        )

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter
    }
}
