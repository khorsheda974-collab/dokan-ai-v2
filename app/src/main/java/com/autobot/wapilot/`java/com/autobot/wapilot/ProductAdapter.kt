package com.autobot.wapilot
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autobot.wapilot.databinding.ItemProductBinding
class ProductAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(b)
    }
    override fun onBindViewHolder(h: ViewHolder, p: Int) { h.binding.tvName.text = list[p].name; h.binding.tvPrice.text = list[p].price }
    override fun getItemCount() = list.size
}
