package com.autobot.wapilot
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        db = Room.databaseBuilder(this, AppDatabase::class.java, "dokan-db")
            .allowMainThreadQueries().build()
            
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(this, 2)
        
        // Product load
        val products = db.productDao().getAll()
        recycler.adapter = ProductAdapter(products, db)
        
        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }
        
        findViewById<FloatingActionButton>(R.id.fabAI).setOnClickListener {
            startActivity(Intent(this, AIChatActivity::class.java))
        }
    }
}
