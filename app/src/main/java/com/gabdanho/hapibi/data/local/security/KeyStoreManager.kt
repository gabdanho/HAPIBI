package com.gabdanho.hapibi.data.local.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * Менеджер для работы с Android Keystore.
 *
 * Генерирует AES-ключ, а также обеспечивает шифрование и дешифрование данных.
 */
class KeyStoreManager {

    private val keyStoreType = "AndroidKeyStore"
    private val alias = "AccessTokenAESKeyAlias"
    private val aesTransformation = "AES/GCM/NoPadding"

    init {
        generateAESKey()
    }

    private fun generateAESKey() {
        val keyStore = KeyStore.getInstance(keyStoreType).apply { load(null) }
        if (!keyStore.containsAlias(alias)) {
            val keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                keyStoreType
            )
            val parameterSpec = KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()

            keyGenerator.init(parameterSpec)
            keyGenerator.generateKey()
        }
    }

    fun encrypt(data: String): String {
        val keyStore = KeyStore.getInstance(keyStoreType).apply { load(null) }
        val secretKey = keyStore.getKey(alias, null) as SecretKey

        val cipher = Cipher.getInstance(aesTransformation)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))

        // Сохраняем IV вместе с шифротекстом
        val result = iv + encrypted
        return Base64.encodeToString(result, Base64.DEFAULT)
    }

    fun decrypt(encryptedBase64: String): String {
        val keyStore = KeyStore.getInstance(keyStoreType).apply { load(null) }
        val secretKey = keyStore.getKey(alias, null) as SecretKey

        val decoded = Base64.decode(encryptedBase64, Base64.DEFAULT)
        val iv = decoded.copyOfRange(0, 12) // IV всегда первые 12 байт
        val encrypted = decoded.copyOfRange(12, decoded.size)

        val cipher = Cipher.getInstance(aesTransformation)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

        return String(cipher.doFinal(encrypted), Charsets.UTF_8)
    }
}
