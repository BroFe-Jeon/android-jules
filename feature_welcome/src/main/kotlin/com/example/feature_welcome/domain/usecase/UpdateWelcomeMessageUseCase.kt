package com.example.feature_welcome.domain.usecase // Updated package

import com.example.feature_welcome.domain.repository.MessageRepository // Updated import
import javax.inject.Inject

class UpdateWelcomeMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(newMessage: String) {
        messageRepository.updateWelcomeMessage(newMessage)
    }
}
