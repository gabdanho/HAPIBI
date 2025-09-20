package com.gabdanho.hapibi.domain.interfaces.repository.local

interface AccessTokenDataSource {

    fun setAccessToken(token: String)

    fun getAccessToken(): String?

    fun deleteAccessToken()
}