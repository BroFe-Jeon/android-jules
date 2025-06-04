package com.example.core.mvi

/**
 * Represents a state of the UI.
 * It should be an immutable data class.
 */
interface UiState

/**
 * Represents an event triggered by the user or the system that the ViewModel needs to process.
 */
interface UiEvent

/**
 * Represents a side effect that the UI should perform once, e.g., showing a toast or navigating.
 */
interface UiEffect
