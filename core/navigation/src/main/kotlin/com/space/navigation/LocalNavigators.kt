package com.space.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalGlobalNavigator = staticCompositionLocalOf<Navigator?> { null }

val LocalFlowNavigator = compositionLocalOf<Navigator?> { null }

@Composable
fun globalNavigator() = LocalGlobalNavigator.current

@Composable
fun localNavigator() = LocalFlowNavigator.current

@Composable
fun requireLocalNavigator(): Navigator {
    return localNavigator() ?: throw IllegalStateException("Local navigator is not available")
}

@Composable
fun requireGlobalNavigator(): Navigator {
    return globalNavigator() ?: throw IllegalStateException("Global navigator is not available")
}