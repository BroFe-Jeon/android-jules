package com.example.feature_welcome.domain.repository

import com.example.feature_welcome.domain.model.Message // Updated import
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getWelcomeMessage(): Flow<Message>
    suspend fun updateWelcomeMessage(newMessage: String)
}
