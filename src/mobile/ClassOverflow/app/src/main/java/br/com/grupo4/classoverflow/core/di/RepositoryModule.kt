package br.com.grupo4.classoverflow.core.di

import br.com.grupo4.classoverflow.data.repository.contract.LoginRepository
import br.com.grupo4.classoverflow.data.repository.contract.QuestionRepository
import br.com.grupo4.classoverflow.data.repository.implementation.LoginRepositoryImpl
import br.com.grupo4.classoverflow.data.repository.implementation.QuestionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindQuestionRepository(repository: QuestionRepositoryImpl): QuestionRepository

    @ViewModelScoped
    @Binds
    abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository
}