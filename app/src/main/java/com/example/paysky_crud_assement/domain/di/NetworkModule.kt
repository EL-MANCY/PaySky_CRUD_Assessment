package com.example.paysky_crud_assement.domain.di


import com.example.paysky_crud_assement.data.remote.Constants.Companion.BASE_URL
import com.example.paysky_crud_assement.data.remote.JsonPlaceholderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newRequest = request.newBuilder()
            .build()
        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .connectTimeout(50, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor { message -> println(message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): JsonPlaceholderApi {
        return retrofit.create(JsonPlaceholderApi::class.java)
    }
}
