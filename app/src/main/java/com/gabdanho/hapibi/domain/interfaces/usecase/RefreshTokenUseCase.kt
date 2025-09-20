package com.gabdanho.hapibi.domain.interfaces.usecase

interface RefreshTokenUseCase {

    suspend operator fun invoke(): String
}