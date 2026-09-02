package com.space.feature.authentication.presentation.auth.flow.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.space.chatapp.core.ui.R
import com.space.feature.authentication.presentation.auth.flow.login.contract.LoginEvent
import com.space.feature.authentication.presentation.auth.flow.login.contract.LoginState
import com.space.feature.authentication.presentation.auth.flow.login.vm.LoginVm
import com.space.presentation.BaseScreen
import com.space.presentation.errorMessageResId
import com.space.presentation.isLoading
import com.space.ui.component.ChatAppTextDivider
import com.space.ui.component.button.ChatAppSwitch
import com.space.ui.component.button.PrimaryButton
import com.space.ui.component.text_field.ChatAppPasswordField
import com.space.ui.component.text_field.ChatAppTextField
import com.space.ui.theme.ChatAppTheme
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Spacing
import com.space.ui.theme.TextSizing

object LoginScreen : BaseScreen<LoginState, LoginEvent>() {
    override val vmClass = LoginVm::class

    @Composable
    override fun Content(
        state: LoginState,
        onEvent: (LoginEvent) -> Unit
    ) {
        LoginContent(state = state, onEvent = onEvent)
    }
}

@Composable
private fun LoginContent(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .imePadding()
            .padding(horizontal = Spacing.spacing24, vertical = Spacing.spacing16)
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        ChatAppSwitch(
            isDarkTheme = state.isDarkTheme,
            activeSwitchIcon = R.drawable.icon_dark,
            modifier = Modifier.align(Alignment.End),
            inActiveIcon = R.drawable.icon_light,
            onCheckChange = { isChecked ->
                onEvent(LoginEvent.OnThemeToggle(isChecked))
            }
        )

        Text(
            text = stringResource(com.space.authentication.presentation.R.string.login),
            fontSize = TextSizing.size30,
            fontWeight = FontWeight.Bold,
            color = colors.textPrimary
        )

        Spacer(modifier = Modifier.height(Spacing.spacing08))

        Text(
            text = stringResource(com.space.authentication.presentation.R.string.welcome),
            fontSize = TextSizing.size16,
            fontWeight = FontWeight.Bold,
            color = colors.textSecondary
        )

        Spacer(modifier = Modifier.height(Spacing.spacing48))

        ChatAppTextField(
            state = state.email,
            label = stringResource(com.space.authentication.presentation.R.string.email)
        )

        Spacer(modifier = Modifier.height(Spacing.spacing12))

        ChatAppPasswordField(
            state = state.password,
            label = stringResource(com.space.authentication.presentation.R.string.password)
        )

        Spacer(modifier = Modifier.height(Spacing.spacing48))

        PrimaryButton(
            text = stringResource(com.space.authentication.presentation.R.string.login),
            enabled = !state.actionState.isLoading,
            onClick = { onEvent(LoginEvent.OnLoginClick) },
            isLoading = state.actionState.isLoading
        )

        Spacer(modifier = Modifier.height(Spacing.spacing28))

        state.actionState.errorMessageResId?.let { errorResId ->
            Text(
                text = stringResource(errorResId),
                color = colors.error,
                fontSize = TextSizing.size14,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Spacing.spacing04),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(Spacing.spacing48))

        ChatAppTextDivider(
            text = stringResource(com.space.authentication.presentation.R.string.or)
        )
    }
}


@Preview
@Composable
private fun LoginContentPreview() {
    ChatAppTheme {
        LoginContent(
            state = LoginState(
                isDarkTheme = true,
            ),
            onEvent = { }
        )
    }
}