package com.gabdanho.hapibi.di

import com.gabdanho.hapibi.domain.interfaces.repository.NetworkRepository
import com.gabdanho.hapibi.domain.interfaces.repository.local.AccessTokenDataSource
import com.gabdanho.hapibi.domain.interfaces.repository.remote.AiRepository
import com.gabdanho.hapibi.domain.interfaces.repository.remote.SocialNetworkRepository
import com.gabdanho.hapibi.domain.interfaces.usecase.DeleteAccessTokenUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.GetAccessTokenUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.GetFriendsUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.HasInternetConnectionUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.LogoutUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.RefreshTokenUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.SendPromptUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.SetAccessTokenUseCase
import com.gabdanho.hapibi.domain.usecase.DeleteAccessTokenUseCaseImpl
import com.gabdanho.hapibi.domain.usecase.GetAccessTokenUseCaseImpl
import com.gabdanho.hapibi.domain.usecase.GetFriendsUseCaseImpl
import com.gabdanho.hapibi.domain.usecase.HasInternetConnectionUseCaseImpl
import com.gabdanho.hapibi.domain.usecase.LogoutUseCaseImpl
import com.gabdanho.hapibi.domain.usecase.RefreshTokenUseCaseImpl
import com.gabdanho.hapibi.domain.usecase.SendPromptUseCaseImpl
import com.gabdanho.hapibi.domain.usecase.SetAccessTokenUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideDeleteAccessTokenUseCase(accessTokenDataSource: AccessTokenDataSource): DeleteAccessTokenUseCase {
        return DeleteAccessTokenUseCaseImpl(accessTokenDataSource = accessTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideGetAccessTokenUseCase(accessTokenDataSource: AccessTokenDataSource): GetAccessTokenUseCase {
        return GetAccessTokenUseCaseImpl(accessTokenDataSource = accessTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideSetAccessTokenUseCase(accessTokenDataSource: AccessTokenDataSource): SetAccessTokenUseCase {
        return SetAccessTokenUseCaseImpl(accessTokenDataSource = accessTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideGetFriendsUseCase(socialNetworkRepository: SocialNetworkRepository): GetFriendsUseCase {
        return GetFriendsUseCaseImpl(socialNetworkRepository = socialNetworkRepository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(socialNetworkRepository: SocialNetworkRepository): LogoutUseCase {
        return LogoutUseCaseImpl(socialNetworkRepository = socialNetworkRepository)
    }

    @Provides
    @Singleton
    fun provideRefreshTokenUseCase(
        socialNetworkRepository: SocialNetworkRepository,
        hasInternetConnectionUseCase: HasInternetConnectionUseCase,
    ): RefreshTokenUseCase {
        return RefreshTokenUseCaseImpl(
            socialNetworkRepository = socialNetworkRepository,
            hasInternetConnectionUseCase = hasInternetConnectionUseCase
        )
    }

    @Provides
    @Singleton
    fun provideSendPromptUseCase(aiRepository: AiRepository): SendPromptUseCase {
        return SendPromptUseCaseImpl(aiRepository = aiRepository)
    }

    @Provides
    @Singleton
    fun provideHasInternetConnectionUseCase(networkRepository: NetworkRepository): HasInternetConnectionUseCase {
        return HasInternetConnectionUseCaseImpl(networkRepository = networkRepository)
    }
}