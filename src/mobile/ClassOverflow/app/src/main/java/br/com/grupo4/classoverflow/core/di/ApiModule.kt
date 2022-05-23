package br.com.grupo4.classoverflow.core.di

import android.content.Context
import br.com.grupo4.classoverflow.data.repository.implementation.SharedPreferencesRepository
import br.com.grupo4.classoverflow.data.api.LoginService
import br.com.grupo4.classoverflow.data.api.QuestionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
class ApiModule {

    companion object {
        const val BASE_URL = "https://puc-garden-backend.herokuapp.com/api/"
    }

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val authInterceptor = Interceptor { chain ->
            val accessToken = SharedPreferencesRepository(context).getAccessToken()
            val requestBuilder = chain.request()
                .newBuilder()
                .header("Authorization", accessToken)

            chain.proceed(requestBuilder.build())
        }

        return OkHttpClient
            .Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideQuestion(retrofit: Retrofit): QuestionService =
        retrofit.create(QuestionService::class.java)

    @Provides
    @Singleton
    fun provideLogin(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)
}