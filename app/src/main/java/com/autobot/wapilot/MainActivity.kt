package com.autobot.wapilot
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        findViewById<Button>(R.id.fabAdd).setOnClickListener {
            Toast.makeText(this, "Add Product Clicked", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.fabAI).setOnClickListener {
            Toast.makeText(this, "AI Chat Clicked", Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this, "SUCCESS: App Opened!", Toast.LENGTH_LONG).show()
    }
}
