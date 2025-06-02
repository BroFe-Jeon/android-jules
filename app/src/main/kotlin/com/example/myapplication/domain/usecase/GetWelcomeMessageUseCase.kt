package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Message
import com.example.myapplication.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWelcomeMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    operator fun invoke(): Flow<Message> = messageRepository.getWelcomeMessage()
}
