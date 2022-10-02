package com.erbeandroid.petfinder.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.erbeandroid.petfinder.core.network.datasource.PetFinderRemoteDataSource
import com.erbeandroid.petfinder.core.network.datasource.RemoteDataSource
import com.erbeandroid.petfinder.core.network.interceptor.AuthenticationInterceptor
import com.erbeandroid.petfinder.core.network.interceptor.NetworkStatusInterceptor
import com.erbeandroid.petfinder.core.network.service.PetFinderService
import com.erbeandroid.petfinder.core.network.util.NetworkConstant.BASE_ENDPOINT
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsRemoteDataSource(
        petFinderRemoteDataSource: PetFinderRemoteDataSource
    ): RemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideMoshi(): Moshi {
            return Moshi.Builder().build()
        }

        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        fun provideChuckCollector(
            @ApplicationContext context: Context
        ): ChuckerCollector {
            return ChuckerCollector(
                context,
                true,
                RetentionManager.Period.ONE_HOUR
            )
        }

        @Provides
        fun provideChuckInterceptor(
            @ApplicationContext context: Context,
            chuckCollector: ChuckerCollector
        ): ChuckerInterceptor {
            return ChuckerInterceptor.Builder(context)
                .collector(chuckCollector)
                .maxContentLength(250000L)
                .redactHeaders("Authorization", "Bearer")
                .alwaysReadResponseBody(false)
                .build()
        }

        @Provides
        fun provideOkHttpClient(
            networkStatusInterceptor: NetworkStatusInterceptor,
            authenticationInterceptor: AuthenticationInterceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
            chuckInterceptor: ChuckerInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(networkStatusInterceptor)
                .addInterceptor(authenticationInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chuckInterceptor)
                .build()
        }

        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(BASE_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
        }

        @Provides
        @Singleton
        fun providePetFinderService(builder: Retrofit.Builder): PetFinderService {
            return builder
                .build()
                .create(PetFinderService::class.java)
        }
    }
}