package com.space.chatapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.space.chatapp.MainActivity
import com.space.feature.authentication.presentation.navigator.authFeatureEntry
import com.space.navigation.LocalGlobalNavigator
import com.space.navigation.rememberNavigator

@Composable
fun MainActivity.ChatAppContainer(
    startDestination: NavKey,
) {
    val navigator = rememberNavigator(startDestination)

    CompositionLocalProvider(
        LocalGlobalNavigator provides navigator
    ) {
        NavDisplay(
            backStack = navigator.backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            onBack = {
                if (navigator.backStack.size > 1) navigator.pop() else finishAffinity()
            },
            entryProvider = entryProvider {
                authFeatureEntry()
            },
        )
    }
}