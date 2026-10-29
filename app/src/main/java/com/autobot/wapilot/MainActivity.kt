package com.autobot.wapilot
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_main)
            findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
                try { startActivity(Intent(this, AddProductActivity::class.java)) } 
                catch(e: Exception) { Toast.makeText(this, "Add Product file missing: ${e.message}", Toast.LENGTH_LONG).show() }
            }
            findViewById<FloatingActionButton>(R.id.fabAI).setOnClickListener {
                try { startActivity(Intent(this, AIChatActivity::class.java)) }
                catch(e: Exception) { Toast.makeText(this, "AI Chat file missing: ${e.message}", Toast.LENGTH_LONG).show() }
            }
            Toast.makeText(this, "Dokan AI V2 Opened!", Toast.LENGTH_SHORT).show()
        } catch(e: Exception) {
            // Jodi layout crash kore, tahole empty screen dekhao
            val tv = android.widget.TextView(this)
            tv.text = "Error: ${e.message}"
            setContentView(tv)
        }
    }
}
