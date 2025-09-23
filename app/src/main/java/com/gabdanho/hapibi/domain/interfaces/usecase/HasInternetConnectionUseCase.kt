package com.gabdanho.hapibi.domain.interfaces.usecase

import kotlinx.coroutines.flow.Flow

/**
 * UseCase для проверки наличия интернет-соединения.
 */
interface HasInternetConnectionUseCase {
    operator fun invoke(): Flow<Boolean>
}