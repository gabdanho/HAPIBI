package com.gabdanho.hapibi.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.gabdanho.hapibi.domain.interfaces.repository.NetworkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * Репозиторий для отслеживания состояния интернет-соединения.
 *
 * @property context Контекст приложения, используется для доступа к системным сервисам.
 */
class NetworkRepositoryImpl @Inject constructor(
    private val context: Context,
) : NetworkRepository {

    /**
     * Отслеживает состояние интернет-соединения в виде [Flow].
     *
     * @return [Flow], эмитирующий `true`, если сеть доступна, и `false`, если соединение потеряно.
     */
    override fun hasInternetConnection(): Flow<Boolean> = callbackFlow {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
        }

        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, callback)

        trySend(connectivityManager.activeNetwork != null)

        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }
}