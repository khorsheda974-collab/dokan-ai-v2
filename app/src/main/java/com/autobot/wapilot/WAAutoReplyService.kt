package com.dokan.ai

import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.core.app.RemoteInput

class WAAutoReplyService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null) return
        val notification = sbn.notification
        val extras = notification.extras
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""
        if (sbn.packageName != "com.whatsapp") return
        if (text.isEmpty()) return

        val replyText = "Dokan AI: Apnar message peyechi - $text"
        val actions = notification.actions ?: return
        for (action in actions) {
            if (action.remoteInputs != null && action.remoteInputs.isNotEmpty()) {
                val remoteInput = action.remoteInputs[0]
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
}
