package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.NetworkRepository
import com.gabdanho.hapibi.domain.interfaces.usecase.HasInternetConnectionUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HasInternetConnectionUseCaseImpl @Inject constructor(
    private val networkRepository: NetworkRepository,
) : HasInternetConnectionUseCase {

    override fun invoke(): Flow<Boolean> {
        return networkRepository.hasInternetConnection()
    }
}