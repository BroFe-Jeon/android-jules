package com.example.feature_welcome.di // Updated package name

import com.example.feature_welcome.data.datasource.local.InMemoryMessageLocalDataSource
import com.example.feature_welcome.data.datasource.local.MessageLocalDataSource
import com.example.feature_welcome.data.repository.MessageRepositoryImpl
import com.example.feature_welcome.domain.repository.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // This installs the bindings at the application level (SingletonComponent)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindMessageRepository(
        messageRepositoryImpl: MessageRepositoryImpl
    ): MessageRepository

    @Binds
    @Singleton
    abstract fun bindMessageLocalDataSource(
        inMemoryMessageLocalDataSource: InMemoryMessageLocalDataSource
    ): MessageLocalDataSource
}
