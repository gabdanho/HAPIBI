package com.gabdanho.hapibi.di

import android.content.Context
import com.gabdanho.hapibi.data.local.datasource.UserTokenDataProvider
import com.gabdanho.hapibi.data.local.repository.UserTokenDataRepository
import com.gabdanho.hapibi.data.local.repository.UserTokenDataRepositoryImpl
import com.gabdanho.hapibi.data.remote.repository.AimlapiService
import com.gabdanho.hapibi.data.remote.repository.VkApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL_VK = "https://api.vk.com"
private const val BASE_URL_OPENAI = "https://api.aimlapi.com"

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Provides
    @Singleton
    fun provideUserTokenDataProvider(
        @ApplicationContext appContext: Context
    ): UserTokenDataProvider {
        return UserTokenDataProvider(appContext)
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(
        dataProvider: UserTokenDataProvider
    ): UserTokenDataRepository {
        return UserTokenDataRepositoryImpl(dataProvider.sharedPreferences)
    }

    @Provides
    @Singleton
    @Named("vkApi")
    fun provideVkApiRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

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
    fun provideVkApiService(@Named("vkApi") retrofit: Retrofit): VkApiService {
        return retrofit.create(VkApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("openAIApi")
    fun provideOpenAIRetrofit(): Retrofit {
        val apiKey = "19004cab1b4f40e2be448401e3857b48" // TODO()

        val gson = GsonBuilder()
            .setLenient()
            .create()

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
    fun provideOpenAiService(@Named("openAIApi") retrofit: Retrofit): AimlapiService {
        return retrofit.create(AimlapiService::class.java)
    }
}