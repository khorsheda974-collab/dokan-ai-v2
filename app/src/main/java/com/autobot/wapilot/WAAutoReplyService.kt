package com.autobot.wapilot

import android.app.Notification
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.core.app.NotificationCompat
import android.app.RemoteInput

class WAAutoReplyService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null) return
        // Only WhatsApp
        if (sbn.packageName != "com.whatsapp" && sbn.packageName != "com.whatsapp.w4b") return

        val notification = sbn.notification
        val extras = notification.extras

        // Check if it's a message notification with reply action
        val actions = notification.actions ?: return
        for (action in actions) {
            if (action.remoteInputs != null && action.remoteInputs.isNotEmpty()) {
                // This is auto reply logic - get store name from prefs later
                val message = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""
                if (message.isEmpty()) continue

                // Simple AI reply - you can customize
                val replyText = "Assalamu Alaikum! Dokan AI theke bolchi. Apnar message peyechi: '$message'. Kivabe help korte pari?"

                val remoteInput = action.remoteInputs[0]
                val bundle = Bundle()
                bundle.putCharSequence(remoteInput.resultKey, replyText)

                val replyIntent = RemoteInput.addResultsToIntent(action.remoteInputs, action.actionIntent, bundle)
                try {
                    action.actionIntent.send(this, 0, replyIntent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
