package org.android.go.sopt.util

import android.app.Application
import org.android.go.sopt.data.MySharedPreferences

class MyApplication : Application() {

    companion object {
        lateinit var mySharedPreferences: MySharedPreferences
    }

    override fun onCreate() {
        mySharedPreferences = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}
