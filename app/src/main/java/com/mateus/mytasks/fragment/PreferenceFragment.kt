package com.mateus.mytasks.fragment

import android.os.Bundle
import android.util.Log
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.mateus.mytasks.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class PreferenceFragment : PreferenceFragmentCompat() {

    companion object {
        const val DAILY_NOTIFICATION_KEY = "daily_notification"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        findPreference<SwitchPreferenceCompat>(DAILY_NOTIFICATION_KEY)?.onPreferenceChangeListener =
            OnPreferenceChangeListener { _, newValue ->
                if (newValue.toString().toBoolean()) {
                    Firebase.messaging.subscribeToTopic(DAILY_NOTIFICATION_KEY).addOnCompleteListener {
                        Log.e("fcm", "Tópico registrado.")
                    }
                } else {
                    Firebase.messaging.unsubscribeFromTopic(DAILY_NOTIFICATION_KEY).addOnCompleteListener {
                        Log.e("fcm", "Tópico desregistrado.")
                    }
                }

                true
            }
    }
}