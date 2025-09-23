package com.gabdanho.hapibi.domain.interfaces.usecase

/**
 * UseCase для получения access token.
 */
interface GetAccessTokenUseCase {

    operator fun invoke(): String?
}