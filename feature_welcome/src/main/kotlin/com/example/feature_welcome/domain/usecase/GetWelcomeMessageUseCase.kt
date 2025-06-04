package com.example.feature_welcome.domain.usecase // Updated package

import com.example.feature_welcome.domain.model.Message // Updated import
import com.example.feature_welcome.domain.repository.MessageRepository // Updated import
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWelcomeMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    operator fun invoke(): Flow<Message> = messageRepository.getWelcomeMessage()
}
