package com.autobot.wapilot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autobot.wapilot.databinding.ItemProductBinding

class ProductAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = product.price
    }
    override fun getItemCount() = list.size
}
