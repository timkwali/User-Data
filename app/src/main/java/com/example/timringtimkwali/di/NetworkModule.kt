package com.example.timringtimkwali.di

import com.example.timringtimkwali.network.API
import com.example.timringtimkwali.network.UsersRepository
import com.example.timringtimkwali.network.UsersRepositoryImpl
import com.example.timringtimkwali.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, converterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository {
        return usersRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }
}