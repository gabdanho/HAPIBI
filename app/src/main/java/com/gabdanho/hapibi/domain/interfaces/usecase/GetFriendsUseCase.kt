package com.gabdanho.hapibi.domain.interfaces.usecase

import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.society.Friend

interface GetFriendsUseCase {

    suspend operator fun invoke(token: String): ApiResult<List<Friend>>
}