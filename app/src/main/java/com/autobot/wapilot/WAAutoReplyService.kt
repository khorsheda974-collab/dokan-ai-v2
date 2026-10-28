package com.dokan.ai

import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.core.app.RemoteInput

class WAAutoReplyService : NotificationListenerService() {
    private val MY_GROUP_JID = "1203630XXXXXXXX@g.us" // এখানে তোমার গ্রুপ JID বসাও

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null) return
        if (sbn.packageName != "com.whatsapp") return
        val text = sbn.notification.extras.getCharSequence("android.text")?.toString() ?: ""
        val title = sbn.notification.extras.getString("android.title") ?: "Customer"
        if (text.isEmpty()) return

        if (text.lowercase().contains("order")) {
            submitOrderToGroup(title, text)
            replyToCustomer(sbn, "ধন্যবাদ! অর্ডার পেয়েছি, গ্রুপে পাঠিয়ে দিয়েছি।")
        } else {
            replyToCustomer(sbn, "Dokan AI: Message peyechi - $text")
        }
    }

    private fun replyToCustomer(sbn: StatusBarNotification, replyText: String) {
        val actions = sbn.notification.actions ?: return
        for (action in actions) {
            if (action.remoteInputs != null && action.remoteInputs.isNotEmpty()) {
                val remoteInput = action.remoteInputs[0]
                val replyIntent = Intent()
                val bundle = Bundle()
                bundle.putCharSequence(remoteInput.resultKey, replyText)
                RemoteInput.addResultsToIntent(arrayOf(remoteInput), replyIntent, bundle)
                try { action.actionIntent.send(this, 0, replyIntent) } catch (e: Exception) { e.printStackTrace() }
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
                `package` = "com.whatsapp"
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        } catch (e: Exception) { e.printStackTrace() }
    }
}
