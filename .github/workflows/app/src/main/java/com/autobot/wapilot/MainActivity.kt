package com.autobot.wapilot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.autobot.wapilot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductAdapter(productList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnAddProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val price = binding.etProductPrice.text.toString()
            if(name.isNotEmpty()){
                productList.add(Product(name, price))
                adapter.notifyDataSetChanged()
            }
        }
    }
}
data class Product(val name: String, val price: String)
