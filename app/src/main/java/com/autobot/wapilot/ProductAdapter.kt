package com.autobot.wapilot
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Product(val productName: String = "", val productPrice: String = "")

class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.productName)
        val priceText: TextView = view.findViewById(R.id.productPrice)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = products[position].productName
        holder.priceText.text = products[position].productPrice
    }
    override fun getItemCount() = products.size
}
