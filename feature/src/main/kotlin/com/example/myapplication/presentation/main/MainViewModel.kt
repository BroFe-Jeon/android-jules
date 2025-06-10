package com.example.myapplication.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.GetWelcomeMessageUseCase
import com.example.myapplication.domain.usecase.UpdateWelcomeMessageUseCase
import com.example.myapplication.mvi.UiEffect
import com.example.myapplication.mvi.UiEvent
import com.example.myapplication.mvi.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

// State
data class MainScreenState(
    val currentMessage: String = "Loading...", // Updated initial message
    val counter: Int = 0,
    val isLoading: Boolean = true // isLoading true initially
) : UiState

// Events
sealed interface MainScreenEvent : UiEvent {
    data class UpdateMessageViaInput(val newMessage: String) : MainScreenEvent // New event for direct message update
    object IncrementCounter : MainScreenEvent
    object RequestInitialMessage : MainScreenEvent // To explicitly request message load
    object ShowToastWithCurrentMessage : MainScreenEvent
}

// Effects
sealed interface MainScreenEffect : UiEffect {
    data class ShowToastMessage(val message: String) : MainScreenEffect
    data class LogErrorMessage(val error: String) : MainScreenEffect
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWelcomeMessageUseCase: GetWelcomeMessageUseCase,
    private val updateWelcomeMessageUseCase: UpdateWelcomeMessageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<MainScreenEffect>()
    val effect: SharedFlow<MainScreenEffect> = _effect.asSharedFlow()

    init {
        // Initial message load can be triggered here or via an event from UI
        onEvent(MainScreenEvent.RequestInitialMessage)
    }

    fun onEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is MainScreenEvent.UpdateMessageViaInput -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                    updateWelcomeMessageUseCase(event.newMessage) // This will update the source
                    // The flow from getWelcomeMessageUseCase should then emit the new value
                    // No direct state update here for message, relying on the flow.
                    // After updating, the flow below should pick it up.
                    // For immediate feedback, could also update state:
                    // _uiState.value = _uiState.value.copy(currentMessage = event.newMessage, isLoading = false)
                }
                is MainScreenEvent.IncrementCounter -> {
                    _uiState.value = _uiState.value.copy(counter = _uiState.value.counter + 1)
                }
                is MainScreenEvent.ShowToastWithCurrentMessage -> {
                    _effect.emit(MainScreenEffect.ShowToastMessage("Msg: \${_uiState.value.currentMessage}, Count: \${_uiState.value.counter}"))
                }
                is MainScreenEvent.RequestInitialMessage -> {
                    getWelcomeMessageUseCase()
                        .onStart { _uiState.value = _uiState.value.copy(isLoading = true) }
                        .catch { e ->
                            _uiState.value = _uiState.value.copy(isLoading = false, currentMessage = "Error loading message")
                            _effect.emit(MainScreenEffect.LogErrorMessage(e.localizedMessage ?: "Unknown error"))
                        }
                        .collect { message ->
                            _uiState.value = _uiState.value.copy(
                                currentMessage = message.content,
                                isLoading = false
                            )
                        }
                }
            }
        }
    }
}
