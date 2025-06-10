package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.local.MessageLocalDataSource
import com.example.myapplication.domain.model.Message
import com.example.myapplication.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // Hilt annotation
class MessageRepositoryImpl @Inject constructor(
    private val localDataSource: MessageLocalDataSource
    // private val remoteDataSource: RemoteDataSource, // If you had one
    // private val messageMapper: MessageMapper // If you had complex mapping
) : MessageRepository {

    override fun getWelcomeMessage(): Flow<Message> {
        return localDataSource.getWelcomeMessage()
        // Example: could also fetch from remote, save to local, then return local flow
    }

    override suspend fun updateWelcomeMessage(newMessage: String) {
        localDataSource.saveWelcomeMessage(Message(newMessage))
    }
}
