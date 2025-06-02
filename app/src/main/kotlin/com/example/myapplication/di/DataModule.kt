package com.example.myapplication.di

import com.example.myapplication.data.datasource.local.InMemoryMessageLocalDataSource
import com.example.myapplication.data.datasource.local.MessageLocalDataSource
import com.example.myapplication.data.repository.MessageRepositoryImpl
import com.example.myapplication.domain.repository.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
