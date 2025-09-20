package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.remote.SocialNetworkRepository
import com.gabdanho.hapibi.domain.interfaces.usecase.GetFriendsUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.society.Friend
import javax.inject.Inject

class GetFriendsUseCaseImpl @Inject constructor(
    private val socialNetworkRepository: SocialNetworkRepository
) : GetFriendsUseCase {

    override suspend fun invoke(token: String): ApiResult<List<Friend>> {
        return socialNetworkRepository.getFriends(accessToken = token)
    }
}