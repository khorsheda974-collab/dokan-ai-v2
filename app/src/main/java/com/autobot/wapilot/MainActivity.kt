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
        
        // FIX: fallbackToDestructiveMigration add korlam jate crash na kore
        db = Room.databaseBuilder(this, AppDatabase::class.java, "dokan-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
            
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = ProductAdapter(db.productDao().getAll(), db)
        
        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener { 
            startActivity(Intent(this, AddProductActivity::class.java)) 
        }
        
        findViewById<FloatingActionButton>(R.id.fabAI).setOnClickListener { 
            startActivity(Intent(this, AIChatActivity::class.java)) 
        }
    }
    
    override fun onResume() { 
        super.onResume()
        try {
            val recycler = findViewById<RecyclerView>(R.id.recycler)
            recycler.adapter = ProductAdapter(db.productDao().getAll(), db)
        } catch(e: Exception) {}
    }
}
