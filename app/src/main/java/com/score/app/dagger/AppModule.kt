package com.score.app.dagger

import android.content.Context
import com.score.app.BuildConfig
import com.score.app.network.ResponseHandler
import com.score.app.network.service.TeamService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttp(context: Context): OkHttpClient.Builder {
        val cacheSize = 5 * 1024 * 1024L // 5 MB
        val cache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient.Builder().cache(cache)
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient.Builder): Retrofit {

        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)

        val retroBuilder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
        retroBuilder.client(httpClient.build())

        return retroBuilder.build()
    }

    @Provides
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }


    // API Services
    @Provides
    @Singleton
    fun provideTeamService(
            retrofit: Retrofit): TeamService {
        return retrofit.create(TeamService::class.java)
    }


}