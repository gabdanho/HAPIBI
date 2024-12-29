package com.gabdanho.hapibi.di

import android.content.Context
import com.gabdanho.hapibi.data.local.datasource.UserTokenDataProvider
import com.gabdanho.hapibi.data.local.repository.UserTokenDataRepository
import com.gabdanho.hapibi.data.local.repository.UserTokenDataRepositoryImpl
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
import javax.inject.Singleton

private const val BASE_URL = "https://api.vk.com"

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
    fun provideRetrofit(): Retrofit {
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
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideVkApiService(retrofit: Retrofit): VkApiService {
        return retrofit.create(VkApiService::class.java)
    }
}