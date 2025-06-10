package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getWelcomeMessage(): Flow<Message>
    suspend fun updateWelcomeMessage(newMessage: String)
}
