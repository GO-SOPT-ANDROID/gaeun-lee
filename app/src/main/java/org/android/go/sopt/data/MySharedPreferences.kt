package org.android.go.sopt.data

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    private val MY_ACCOUNT: String = "account"
    private val prefs: SharedPreferences =
        context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)

    fun setUserId(input: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_ID", input)
        editor.commit()
    }

    fun getUserId(): String {
        return prefs.getString("MY_ID", "").toString()
    }

    fun setUserPass(input: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_PASS", input)
        editor.commit()
    }

    fun getUserPass(): String {
        return prefs.getString("MY_PASS", "").toString()
    }

    fun setUserName(input: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_NAME", input)
        editor.commit()
    }

    fun getUserName(): String {
        return prefs.getString("MY_NAME", "").toString()
    }

    fun setUserSpec(input: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_SPEC", input)
        editor.commit()
    }

    fun getUserSpec(): String {
        return prefs.getString("MY_SPEC", "").toString()
    }

    fun clearUser() {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
}
