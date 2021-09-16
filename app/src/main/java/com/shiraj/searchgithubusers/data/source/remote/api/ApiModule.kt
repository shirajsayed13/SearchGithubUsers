package com.shiraj.searchgithubusers.data.source.remote.api

import androidx.viewbinding.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private external fun baseUrlFromJNI(): String

    @Provides
    @Singleton
    internal fun provideBaseUrl(): String {

        object {
            init {
                System.loadLibrary("searchgithubusers")
            }
        }
        return baseUrlFromJNI()
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideRetrofit(moshi: Moshi) = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideGithubService(retrofit: Retrofit) = retrofit
        .create(GithubApi::class.java)
}