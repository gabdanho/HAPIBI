package com.gabdanho.hapibi.di

import android.content.Context
import com.gabdanho.hapibi.data.local.datasource.AccessTokenSharedPreferences
import com.gabdanho.hapibi.data.local.security.KeyStoreManager
import com.gabdanho.hapibi.data.remote.api.AiService
import com.gabdanho.hapibi.data.remote.api.VkApiService
import com.gabdanho.hapibi.data.repository.impl.remote.AiRepositoryImpl
import com.gabdanho.hapibi.data.repository.impl.remote.SocialNetworkRepositoryImpl
import com.gabdanho.hapibi.data.utils.NetworkRepositoryImpl
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
import com.google.gson.GsonBuilder
import com.vk.id.VKID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL_VK = "https://api.vk.com/"
private const val BASE_URL_OPENAI = "https://api.zukijourney.com/v1/"

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Provides
    @Singleton
    fun provideVkId(): VKID = VKID.instance

    @Provides
    @Singleton
    fun provideUserTokenDataProvider(): KeyStoreManager = KeyStoreManager()

    @Provides
    @Singleton
    fun provideAccessTokenDataSource(
        @ApplicationContext context: Context,
        keyStoreManager: KeyStoreManager,
    ): AccessTokenDataSource {
        return AccessTokenSharedPreferences(context = context, keyStoreManager = keyStoreManager)
    }

    @Provides
    @Singleton
    @VkApi
    fun provideVkApiRetrofit(): Retrofit {
        val gson = GsonBuilder().create()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL_VK)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @AiApi
    fun provideOpenAIRetrofit(): Retrofit {
        val apiKey = "zu-b22684747c405ec01193a54729f39718"

        val gson = GsonBuilder().create()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                    .build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL_OPENAI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideVkApiService(@VkApi retrofit: Retrofit): VkApiService {
        return retrofit.create(VkApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAiService(@AiApi retrofit: Retrofit): AiService {
        return retrofit.create(AiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAiRepository(aiService: AiService): AiRepository {
        return AiRepositoryImpl(aiService = aiService)
    }

    @Provides
    @Singleton
    fun provideSocialNetworkRepository(
        vkApiService: VkApiService,
        vkid: VKID,
    ): SocialNetworkRepository {
        return SocialNetworkRepositoryImpl(vkApiService = vkApiService, vkid = vkid)
    }

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
    fun provideNetworkRepository(@ApplicationContext context: Context): NetworkRepository {
        return NetworkRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideHasInternetConnectionUseCase(networkRepository: NetworkRepository): HasInternetConnectionUseCase {
        return HasInternetConnectionUseCaseImpl(networkRepository = networkRepository)
    }
}