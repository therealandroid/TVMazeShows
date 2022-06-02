package com.diogojayme.libcore.injection

import android.content.Context
import com.diogojayme.libcore.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Singleton
    @Provides
    fun provideBaseUrl(@ApplicationContext context: Context) =
        context.getString(R.string.tv_shows_url)
            .toHttpUrl()

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: HttpUrl): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

}