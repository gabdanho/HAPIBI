package com.gabdanho.hapibi.data.local.datasource

import android.content.Context
import android.content.SharedPreferences
import com.gabdanho.hapibi.data.local.security.KeyStoreManager
import androidx.core.content.edit
import com.gabdanho.hapibi.domain.interfaces.repository.local.AccessTokenDataSource
import javax.inject.Inject

/**
 * Хранилище access token в зашифрованном виде.
 *
 * @property preferences SharedPreferences для хранения токена.
 * @property keyStoreManager Менеджер для шифрования и дешифрования токенов.
 */
class AccessTokenSharedPreferences @Inject constructor(
    context: Context,
    private val keyStoreManager: KeyStoreManager
) : AccessTokenDataSource {

    val preferences: SharedPreferences = context.getSharedPreferences("secure_prefs", Context.MODE_PRIVATE)

    override fun setAccessToken(token: String) {
        val encryptedToken = keyStoreManager.encrypt(token)
        preferences.edit { putString(KEY_ACCESS_TOKEN, encryptedToken) }
    }

    override fun getAccessToken(): String? {
        val encryptedToken = preferences.getString(KEY_ACCESS_TOKEN, null)
        return encryptedToken?.let { keyStoreManager.decrypt(it) }
    }

    override fun deleteAccessToken() {
        preferences.edit { remove(KEY_ACCESS_TOKEN) }
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
    }
}