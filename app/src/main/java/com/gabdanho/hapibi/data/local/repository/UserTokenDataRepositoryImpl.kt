package com.gabdanho.hapibi.data.local.repository

import android.content.SharedPreferences


const val USER_KEY = "userKey"

class UserTokenDataRepositoryImpl(
    private val sharedPreferences: SharedPreferences
): UserTokenDataRepository {
    override fun getUserKey(): String {
        return sharedPreferences.getString(USER_KEY, "")!!
    }

    override fun putUserKey(token: String) {
        sharedPreferences.edit()
            .putString(USER_KEY, token)
            .apply()
    }
}