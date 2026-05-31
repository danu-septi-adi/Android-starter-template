package com.template.app

import android.app.Application

class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init any third-party SDKs here (analytics, crash reporting, etc.)
        // Example: FirebaseApp.initializeApp(this)
    }
}
