package com.autobot.wapilot
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Code diye UI banailam, kono XML lagbe na - 100% crash free
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 100, 50, 50)
        layout.gravity = android.view.Gravity.CENTER
        
        val tv = TextView(this)
        tv.text = "Dokan AI V2\nSUCCESSFULLY OPENED!"
        tv.textSize = 22f
        tv.gravity = android.view.Gravity.CENTER
        layout.addView(tv)
        
        val btn = Button(this)
        btn.text = "TEST OK"
        btn.setOnClickListener { 
            Toast.makeText(this, "App kaj korche!", Toast.LENGTH_LONG).show() 
        }
        layout.addView(btn)
        
        setContentView(layout)
        Toast.makeText(this, "App Opened!", Toast.LENGTH_SHORT).show()
    }
}
