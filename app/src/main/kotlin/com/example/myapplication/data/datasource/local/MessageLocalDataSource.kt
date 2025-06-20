package com.example.myapplication.data.datasource.local

import com.example.myapplication.domain.model.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface MessageLocalDataSource {
    fun getWelcomeMessage(): Flow<Message>
    suspend fun saveWelcomeMessage(message: Message)
}

@Singleton // Hilt annotation
class InMemoryMessageLocalDataSource @Inject constructor() : MessageLocalDataSource {
    private val currentMessage = MutableStateFlow(Message("Hello from Data Layer!"))

    override fun getWelcomeMessage(): Flow<Message> = currentMessage
    override suspend fun saveWelcomeMessage(message: Message) {
        currentMessage.value = message
    }
}
