package com.autobot.wapilot
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
class WAAutoReplyService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if(sbn?.packageName?.contains("whatsapp") == true){ }
    }
}
