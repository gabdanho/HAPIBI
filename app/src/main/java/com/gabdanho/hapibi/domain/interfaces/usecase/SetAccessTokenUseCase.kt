package com.gabdanho.hapibi.domain.interfaces.usecase

interface SetAccessTokenUseCase {

    operator fun invoke(token: String)
}