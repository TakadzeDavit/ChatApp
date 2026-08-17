package com.space.feature.authentication.presentation.navigator

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.space.feature.authentication.api.AuthFeatureKey
import com.space.feature.authentication.presentation.auth.feature.screen.AuthFeatureScreen
import com.space.feature.authentication.presentation.auth.flow.routing.screen.screen.RoutingScreen

fun EntryProviderScope<NavKey>.authFeatureEntry() {
    entry<AuthFeatureKey> {
        AuthFeatureScreen()
    }
}

internal fun EntryProviderScope<NavKey>.authFlowEntry() {
    entry<RouteScreenKey> {
        RoutingScreen()
    }
}