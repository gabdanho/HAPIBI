package com.gabdanho.hapibi.data.local.repository

interface UserTokenDataRepository {
    fun getUserKey(): String
    fun putUserKey(token: String)
}