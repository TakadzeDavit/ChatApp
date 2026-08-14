package com.space.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.scope.KoinScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@OptIn(KoinExperimentalAPI::class)
@Composable
fun <UIState : UiState, UIEvent : UiEvent> BaseScreen(
    vmClass: VmClass<UIState, UIEvent>,
    scopeQualifier: Qualifier,
    parameters: ParametersDefinition? = null,
    content: @Composable (state: UIState, onEvent: (UIEvent) -> Unit) -> Unit
) {
    KoinScope(
        scopeDefinition = { getOrCreateScope(scopeQualifier.value, scopeQualifier) }
    ) {
        val viewModel = koinViewModel(
            vmClass = vmClass,
            parameters = parameters
        )
        val state by viewModel.state.collectAsStateWithLifecycle()
        NavCommands(viewModel.navigationCommands)
        content(state, viewModel::onEvent)
    }
}