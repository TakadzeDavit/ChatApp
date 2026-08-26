package com.space.presentation

import androidx.lifecycle.ViewModel
import com.space.navigation.FeatureNavigationHelper
import com.space.navigation.FlowNavigationHelper
import com.space.navigation.NavCommandBundle
import com.space.navigation.NavigationCommand
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

interface UiEvent
interface UiState

abstract class BaseVm<State : UiState, Event : UiEvent>(
    initialState: State
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()


    private val _navigationCommands = Channel<NavCommandBundle>(Channel.BUFFERED)
    val navigationCommands = _navigationCommands.receiveAsFlow()

    abstract fun onEvent(event: Event)

    protected fun updateState(update: State.() -> State) {
        _state.update { currentState ->
            currentState.update()
        }
    }

    protected fun globalNavigator(navigation: FeatureNavigationHelper.() -> NavigationCommand) {
        _navigationCommands.trySend(
            NavCommandBundle(
                featureNavigationCommand = navigation.invoke(FeatureNavigationHelper)
            )
        )
    }

    protected fun flowNavigator(navigation: FlowNavigationHelper.() -> NavigationCommand) {
        _navigationCommands.trySend(
            NavCommandBundle(
                flowNavigationCommand = navigation.invoke(FlowNavigationHelper)
            )
        )
    }
}