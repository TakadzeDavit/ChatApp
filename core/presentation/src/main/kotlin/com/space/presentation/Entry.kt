package com.space.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import org.koin.compose.currentKoinScope
import org.koin.core.annotation.KoinInternalApi
import org.koin.viewmodel.defaultExtras
import org.koin.viewmodel.resolveViewModel

@OptIn(KoinInternalApi::class)
@Composable
@PublishedApi
internal fun <UIState : UiState, UIEvent : UiEvent> BaseScreen<UIState, UIEvent>.Render() {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    val viewModel = resolveViewModel(
        vmClass = vmClass,
        viewModelStore = viewModelStoreOwner.viewModelStore,
        extras = defaultExtras(viewModelStoreOwner),
        scope = currentKoinScope(),
        parameters = parameters
    )
    val state by viewModel.state.collectAsStateWithLifecycle()
    NavCommands(viewModel.navigationCommands)
    Content(state, viewModel::onEvent)
}

inline fun <reified T : NavKey> EntryProviderScope<NavKey>.screen(
    noinline screenProvider: (T) -> BaseScreen<*, *>
) {
    entry<T> { key -> screenProvider(key).Render() }
}