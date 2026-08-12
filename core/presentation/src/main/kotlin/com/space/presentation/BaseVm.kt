package com.space.presentation

import androidx.lifecycle.ViewModel
import com.space.navigation.NavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface UiEvent
interface UiState

abstract class BaseVM<State : UiState, Event : UiEvent>(
    initialState: State
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()


    internal val navigationCommands = MutableSharedFlow<NavigationCommand>(
        extraBufferCapacity = 64
    )

    abstract fun onEvent(event: Event)

    protected fun updateState(update: State.() -> State) {
        _state.update { currentState ->
            currentState.update()
        }
    }
}