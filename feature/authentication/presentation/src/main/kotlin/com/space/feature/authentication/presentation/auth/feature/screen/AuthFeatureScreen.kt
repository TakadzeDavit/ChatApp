package com.space.feature.authentication.presentation.auth.feature.screen

import androidx.compose.runtime.Composable
import com.space.feature.authentication.presentation.navigator.RouteScreenKey
import com.space.feature.authentication.presentation.navigator.authFlowEntry
import com.space.presentation.FlowContainer
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AuthFeatureScreen() {
    FlowContainer(
        initialKey = RouteScreenKey,
        entry = {
            authFlowEntry()
        }
    )
}