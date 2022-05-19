package br.com.grupo4.classoverflow.core.di

import android.content.Context
import br.com.grupo4.classoverflow.data.repository.implementation.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesRepository(@ApplicationContext appContext: Context): SharedPreferencesRepository {
        return SharedPreferencesRepository(appContext)
    }
}