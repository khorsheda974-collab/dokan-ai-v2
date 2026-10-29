package com.autobot.wapilot
import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import android.view.Gravity

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(50,150,50,50)

        val tv = TextView(this)
        tv.text = "Dokan AI V2\nOPENED SUCCESSFULLY!"
        tv.textSize = 24f
        tv.gravity = Gravity.CENTER
        layout.addView(tv)

        val btn = Button(this)
        btn.text = "TEST OK - Kaj Korche"
        btn.setOnClickListener { Toast.makeText(this, "Finally Open Hoise!", Toast.LENGTH_LONG).show() }
        layout.addView(btn)

        setContentView(layout)
    }
}
