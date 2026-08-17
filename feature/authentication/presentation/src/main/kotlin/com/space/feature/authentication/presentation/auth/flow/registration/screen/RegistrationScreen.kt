package com.space.feature.authentication.presentation.auth.flow.registration.screen

import androidx.compose.runtime.Composable
import com.space.domain.RegistrationScope
import com.space.presentation.BaseScreen
import org.koin.core.qualifier.named

@Composable
fun RegistrationScreen() {
    BaseScreen(
        vmClass = RegistrationVm::class,
        scopeQualifier = named<RegistrationScope>(),
        content = { state, onEvent ->

        }
    )
}