package com.space.feature.authentication.presentation.navigator

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.space.domain.RegistrationScope
import com.space.domain.RoutingScope
import com.space.feature.authentication.api.AuthFeatureKey
import com.space.feature.authentication.presentation.auth.feature.screen.AuthFeatureScreen
import com.space.feature.authentication.presentation.auth.flow.registration.screen.RegistrationScreen
import com.space.feature.authentication.presentation.auth.flow.routing.screen.screen.RoutingScreen
import com.space.navigation.scopedEntry

fun EntryProviderScope<NavKey>.authFeatureEntry() {
    entry<AuthFeatureKey> {
        AuthFeatureScreen()
    }
}

internal fun EntryProviderScope<NavKey>.authFlowEntry() {
    scopedEntry<RouteScreenKey, RoutingScope> {
        RoutingScreen()
    }
    scopedEntry<RegistrationScreenKey, RegistrationScope> {
        RegistrationScreen()
    }
}