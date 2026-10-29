package com.autobot.wapilot
import android.graphics.Bitmap
import androidx.palette.graphics.Palette
import com.google.ai.client.generativeai.GenerativeModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

object AIHelper {
    // Pic theke auto colour ber korbe - tomake likhte hobe na
    fun getDominantColour(bitmap: Bitmap, callback: (String, String) -> Unit) {
        Palette.from(bitmap).generate { palette ->
            val swatch = palette?.dominantSwatch
            if (swatch != null) {
                val hex = String.format("#%06X", 0xFFFFFF and swatch.rgb)
                val colourName = getColourName(hex)
                callback(colourName, hex)
            }
        }
    }

    private fun getColourName(hex: String): String {
        return when {
            hex.contains("FF0000", true) -> "Lal"
            hex.contains("0000FF", true) -> "Neel"
            hex.contains("000000", true) -> "Kalo"
            hex.contains("FFFFFF", true) -> "Sada"
            else -> "Mixed Colour"
        }
    }

    // Gemini diye customer er SS er sathe tomar store milabe
    suspend fun findProductMatch(customerImage: Bitmap, productList: List<Product>, apiKey: String): String {
        val model = GenerativeModel(modelName = "gemini-1.5-flash", apiKey = apiKey)
        val prompt = """
            Tumi ekjon dokan er AI. Tomar store e ei products ache: $productList
            Customer ei chobi ta pathiyeche. Eta kon product er sathe mile? Kon colour? 
            Stock ache kina check koro. Bangla/Banglish/English jevabe jigges korse oivabei sundor kore dam, stock, details diye reply dao.
        """.trimIndent()
        val response = model.generateContent(prompt)
        return response.text ?: "Dukkhito, bujhte parini."
    }
}
