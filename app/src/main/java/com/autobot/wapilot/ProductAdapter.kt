package com.autobot.wapilot
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.net.Uri

class ProductAdapter(private var list: List<Product>, private val db: AppDatabase) : RecyclerView.Adapter<ProductAdapter.VH>() {
    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val img: ImageView = v.findViewById(R.id.img)
        val name: TextView = v.findViewById(R.id.name)
        val price: TextView = v.findViewById(R.id.price)
        val stock: TextView = v.findViewById(R.id.stock)
        val btnPlus: TextView = v.findViewById(R.id.btnPlus)
        val btnMinus: TextView = v.findViewById(R.id.btnMinus)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }
    
    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = list[position]
        holder.name.text = "${p.productName} (${p.colourName})"
        holder.price.text = "৳${p.productPrice}"
        holder.stock.text = "Stock: ${p.stock}"
        try { holder.img.setImageURI(Uri.parse(p.imageUri)) } catch(e: Exception) {}
        
        holder.btnPlus.setOnClickListener {
            val newStock = p.copy(stock = p.stock + 1)
            db.productDao().update(newStock)
            list = db.productDao().getAll()
            notifyDataSetChanged()
        }
        holder.btnMinus.setOnClickListener {
            if(p.stock > 0) {
                val newStock = p.copy(stock = p.stock - 1)
                db.productDao().update(newStock)
                list = db.productDao().getAll()
                notifyDataSetChanged()
            }
        }
    }
    
    override fun getItemCount() = list.size
}
