package com.autobot.wapilot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.productName)
        val priceText: TextView = view.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = products[position]
        // ekhane productName use kori nai, tai ar Unresolved asbe na
        holder.nameText.text = if (p.productName.isNotEmpty()) p.productName else p.name
        holder.priceText.text = if (p.productPrice.isNotEmpty()) p.productPrice else p.price
    }

    override fun getItemCount(): Int = products.size
}
