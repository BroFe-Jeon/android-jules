package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MessageRepository
import javax.inject.Inject

class UpdateWelcomeMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(newMessage: String) {
        messageRepository.updateWelcomeMessage(newMessage)
    }
}
