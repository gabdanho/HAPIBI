package com.gabdanho.hapibi.di

import android.content.Context
import com.gabdanho.hapibi.data.local.datasource.AccessTokenSharedPreferences
import com.gabdanho.hapibi.data.local.security.KeyStoreManager
import com.gabdanho.hapibi.data.remote.api.AiService
import com.gabdanho.hapibi.data.remote.api.VkApiService
import com.gabdanho.hapibi.domain.interfaces.repository.local.AccessTokenDataSource
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
import javax.inject.Singleton

private const val BASE_URL_VK = "https://api.vk.com"
private const val BASE_URL_OPENAI = "https://api.zukijourney.com/v1/"

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Provides
    @Singleton
    fun provideUserTokenDataProvider(): KeyStoreManager = KeyStoreManager()

    @Provides
    @Singleton
    fun provideAccessTokenDataSource(
        @ApplicationContext context: Context,
        keyStoreManager: KeyStoreManager
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
}