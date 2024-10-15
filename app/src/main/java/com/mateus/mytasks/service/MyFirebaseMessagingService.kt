package com.mateus.mytasks.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.e("fcm", "Mensagem recebida: ${message.notification?.title}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.e("fcm", "Novo token: $token")
    }
}