package com.space.feature.authentication.presentation.auth.feature.screen

import androidx.compose.runtime.Composable
import com.space.domain.scope.AuthScope
import com.space.feature.authentication.presentation.navigator.RouteScreenKey
import com.space.feature.authentication.presentation.navigator.authFlowEntry
import com.space.presentation.FlowContainer
import org.koin.compose.scope.KoinScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AuthFeatureScreen() {
    KoinScope(
        scopeDefinition = {
            getOrCreateScope(
                scopeId = named<AuthScope.Parent>().value,
                qualifier = named<AuthScope.Parent>()
            )
        }
    ) {
        FlowContainer(
            initialKey = RouteScreenKey,
            entry = {
                authFlowEntry()
            }
        )
    }
}