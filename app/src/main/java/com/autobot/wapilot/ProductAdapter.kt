package com.autobot.wapilot
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Product(val productName: String, val productPrice: String)

class ProductAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductAdapter.VH>() {
    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.productName)
        val price: TextView = v.findViewById(R.id.productPrice)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return VH(view)
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.name.text = list[position].productName
        holder.price.text = list[position].productPrice
    }
    override fun getItemCount(): Int = list.size
}
