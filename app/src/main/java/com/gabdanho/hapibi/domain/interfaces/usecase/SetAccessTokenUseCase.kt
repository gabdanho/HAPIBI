package com.gabdanho.hapibi.domain.interfaces.usecase

/**
 * UseCase для сохранения access token.
 */
interface SetAccessTokenUseCase {

    operator fun invoke(token: String)
}