package com.autobot.wapilot

import android.app.RemoteInput
import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class WAAutoReplyService : NotificationListenerService() {

    private val MY_GROUP_JID = "1203630XXXXXXXX@g.us"

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null) return
        if (sbn.packageName != "com.whatsapp") return
        
        val extras = sbn.notification.extras
        val title = extras.getString("android.title") ?: "Customer"
        val text = extras.getCharSequence("android.text")?.toString() ?: ""
        if (text.isEmpty()) return

        if (text.lowercase().contains("order")) {
            submitOrderToGroup(title, text)
            replyToCustomer(sbn, "ধন্যবাদ! আপনার অর্ডার পেয়েছি, গ্রুপে পাঠিয়েছি।")
        } else {
            replyToCustomer(sbn, "Dokan AI: $text")
        }
    }

    private fun replyToCustomer(sbn: StatusBarNotification, replyText: String) {
        val actions = sbn.notification.actions ?: return
        for (action in actions) {
            val remoteInputs = action.remoteInputs ?: continue
            if (remoteInputs.isNotEmpty()) {
                val remoteInput = remoteInputs[0]
                val replyIntent = Intent()
                val bundle = Bundle()
                bundle.putCharSequence(remoteInput.resultKey, replyText)
                RemoteInput.addResultsToIntent(arrayOf(remoteInput), replyIntent, bundle)
                try {
                    action.actionIntent.send(this, 0, replyIntent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun submitOrderToGroup(customer: String, order: String) {
        val msg = "🛒 New Order\n👤 $customer\n📦 $order"
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, msg)
                putExtra("jid", MY_GROUP_JID)
                type = "text/plain"
                setPackage("com.whatsapp")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
