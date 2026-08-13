package com.space.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.space.navigation.FlowNavigationKey
import com.space.navigation.LocalFlowNavigator
import com.space.navigation.rememberNavigator
import com.space.navigation.requireGlobalNavigator
import com.space.presentation.scene.rememberBottomSheetSceneStrategy
import com.space.presentation.scene.rememberDialogSceneStrategy


@Composable
fun FlowContainer(
    initialKey: FlowNavigationKey,
    entry: EntryProviderScope<NavKey>.() -> Unit
) {
    val globalNavigator = requireGlobalNavigator()
    val navigator = rememberNavigator(initialKey)

    CompositionLocalProvider(LocalFlowNavigator provides navigator) {
        NavDisplay(
            backStack = navigator.backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            sceneStrategies = listOf(
                rememberBottomSheetSceneStrategy(),
                rememberDialogSceneStrategy(),
                remember { SinglePaneSceneStrategy() }
            ),
            onBack =
                { if (navigator.backStack.size > 1) navigator.pop() else globalNavigator.pop() },
            entryProvider = entryProvider {
                entry.invoke(this)
            }
        )
    }
}