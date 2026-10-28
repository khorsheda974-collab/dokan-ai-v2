package com.autobot.wapilot

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class WAAutoReplyService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if (sbn?.packageName == "com.whatsapp" || sbn?.packageName == "com.whatsapp.w4b") {
            val extras = sbn.notification.extras
            val title = extras.getString(Notification.EXTRA_TITLE) ?: ""
            val text = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""
            
            // Auto Reply Logic
            if (text.isNotEmpty()) {
                // তোমার দোকানের Auto Reply
                // এখানে তোমার Product List থেকে Reply করবে
            }
        }
    }
}
