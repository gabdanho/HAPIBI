package com.gabdanho.hapibi.data.remote.model

import com.gabdanho.hapibi.domain.model.ApiResult
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

/**
 * Безопасный API вызов с обработкой ошибок.
 *
 * @param timeoutMillis Таймаут вызова (ms)
 * @param apiCall Lambda с вызовом Firebase
 * @return Результат [ApiResult]
 */
suspend fun <T> safeApiCall(
    timeoutMillis: Long = 30000,
    apiCall: suspend () -> T,
): ApiResult<T> {
    return try {
        val result = withTimeout(timeoutMillis) {
            apiCall()
        }
        ApiResult.Success(data = result)
    } catch (e: TimeoutCancellationException) {
        ApiResult.Error.TimeoutError(timeoutMessage = e.toString())
    } catch (e: HttpException) {
        ApiResult.Error.ServerError(serverMessage = e.toString())
    } catch (e: IOException) {
        ApiResult.Error.ConnectionError(connectionMessage = e.toString())
    } catch (e: Exception) {
        ApiResult.Error.UnknownError(unknownMessage = e.toString())
    }
}