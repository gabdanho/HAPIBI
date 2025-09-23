package com.gabdanho.hapibi.domain.interfaces.repository.local

/**
 * Источник данных для работы с access token.
 */
interface AccessTokenDataSource {

    /**
     * Сохраняет access token.
     *
     * @param token Токен доступа.
     */
    fun setAccessToken(token: String)

    /**
     * Получает сохранённый access token.
     *
     * @return Строка с токеном или `null`, если он отсутствует.
     */
    fun getAccessToken(): String?

    /**
     * Удаляет сохранённый access token.
     */
    fun deleteAccessToken()
}