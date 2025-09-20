package com.gabdanho.hapibi.data.local.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import javax.crypto.Cipher

class KeyStoreManager {

    private val keyStoreType = "AndroidKeyStore"
    private val alias = "AccessTokenKeyAlias"
    private val cipherTransformation = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"

    init {
        generateRSAKeyPair()
    }

    fun generateRSAKeyPair() {
        val keyStore = KeyStore.getInstance(keyStoreType).apply { load(null) }
        if (!keyStore.containsAlias(alias)) {
            val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_RSA,
                keyStoreType
            )

            val parameterSpec = KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                .build()

            kpg.initialize(parameterSpec)
            kpg.generateKeyPair()
        }
    }

    fun encrypt(data: String): String {
        val keyStore = KeyStore.getInstance(keyStoreType).apply { load(null) }
        val publicKey = keyStore.getCertificate(alias).publicKey

        val cipher = Cipher.getInstance(cipherTransformation)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))

        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    fun decrypt(encryptedBase64: String): String {
        val keyStore = KeyStore.getInstance(keyStoreType).apply { load(null) }
        val privateKey = keyStore.getKey(alias, null) as PrivateKey

        val cipher = Cipher.getInstance(cipherTransformation)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decoded = Base64.decode(encryptedBase64, Base64.DEFAULT)

        return String(cipher.doFinal(decoded), Charsets.UTF_8)
    }
}