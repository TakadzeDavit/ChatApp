package com.space.feature.authentication.presentation.auth.flow.registration.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.space.chatapp.core.ui.R
import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationEvent
import com.space.feature.authentication.presentation.auth.flow.registration.contract.RegistrationState
import com.space.feature.authentication.presentation.auth.flow.registration.vm.RegistrationVm
import com.space.presentation.BaseScreen
import com.space.ui.component.button.ChatAppSwitch
import com.space.ui.component.button.PrimaryButton
import com.space.ui.component.text_field.ChatAppPasswordField
import com.space.ui.component.text_field.ChatAppTextField
import com.space.ui.theme.ChatAppTheme
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Spacing

@Composable
fun RegistrationScreen() {
    BaseScreen(
        vmClass = RegistrationVm::class,
        content = { state, onEvent ->
            RegistrationContent(
                state = state,
                onEvent = onEvent
            )
        }
    )
}

@Composable
private fun RegistrationContent(
    state: RegistrationState,
    onEvent: (RegistrationEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .statusBarsPadding()
            .imePadding()
            .padding(horizontal = Spacing.spacing24),
        contentPadding = PaddingValues(
            bottom = 24.dp
        )
    ){
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                ChatAppSwitch(
                    isDarkTheme = state.isDarkTheme,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(top = 16.dp),
                    activeSwitchIcon = R.drawable.icon_dark,
                    inActiveIcon = R.drawable.icon_light,
                    onCheckChange = { isChecked ->
                        onEvent(RegistrationEvent.OnThemeToggle(isChecked))
                    }
                )
            }

            Spacer(modifier = Modifier.height(Spacing.spacing24))
        }

        item {
            Text(
                text = stringResource(com.space.authentication.presentation.R.string.registration),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary
            )

            Spacer(modifier = Modifier.height(Spacing.spacing12))
        }

        item {
            Text(
                text = stringResource(com.space.authentication.presentation.R.string.create_new),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = colors.textSecondary
            )

            Spacer(modifier = Modifier.height(Spacing.spacing28))
        }

        item {
            ChatAppTextField(
                state = state.name,
                label = stringResource(com.space.authentication.presentation.R.string.name),
            )

            Spacer(modifier = Modifier.height(Spacing.spacing12))
        }

        item {
            ChatAppTextField(
                state = state.email,
                label = stringResource(com.space.authentication.presentation.R.string.email)
            )

            Spacer(modifier = Modifier.height(Spacing.spacing12))
        }

        item {
            ChatAppPasswordField(
                state = state.password,
                label = stringResource(com.space.authentication.presentation.R.string.password)
            )

            Spacer(modifier = Modifier.height(Spacing.spacing12))
        }

        item {
            ChatAppPasswordField(
                state = state.repeatPassword,
                label = stringResource(com.space.authentication.presentation.R.string.repeat_password)
            )

            Spacer(modifier = Modifier.height(Spacing.spacing48))
        }

        item {
            PrimaryButton(
                text = stringResource(com.space.authentication.presentation.R.string.next),
                onClick = {  }
            )
        }
    }
}


@Preview
@Composable
private fun RegisterPreview() {
    ChatAppTheme {
        RegistrationContent(
            state = RegistrationState(),
            onEvent = {}
        )
    }
}