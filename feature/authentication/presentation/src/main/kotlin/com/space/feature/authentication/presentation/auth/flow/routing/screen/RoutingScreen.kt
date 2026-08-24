package com.space.feature.authentication.presentation.auth.flow.routing.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.space.authentication.presentation.R
import com.space.feature.authentication.presentation.auth.flow.routing.contract.RoutingEvent
import com.space.feature.authentication.presentation.auth.flow.routing.contract.RoutingState
import com.space.feature.authentication.presentation.auth.flow.routing.vm.RoutingVm
import com.space.presentation.BaseScreen
import com.space.ui.component.button.ChatAppSwitch
import com.space.ui.component.button.PrimaryButton
import com.space.ui.component.button.SecondaryButton
import com.space.ui.theme.ChatAppTheme
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

@Composable
fun RoutingScreen() {
    BaseScreen(
        vmClass = RoutingVm::class,
        content = { state, onEvent ->
            RoutingContent(
                state = state,
                onEvent = onEvent
            )
        }
    )
}

@Composable
fun RoutingContent(
    state: RoutingState,
    onEvent: (RoutingEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = Spacing.spacing24)
    ) {
        ChatAppSwitch(
            isDarkTheme = state.isDarkTheme,
            modifier = Modifier.align(Alignment.TopEnd).padding(top = 16.dp),
            activeSwitchIcon = com.space.chatapp.core.ui.R.drawable.icon_dark,
            inActiveIcon = com.space.chatapp.core.ui.R.drawable.icon_light,
            onCheckChange = { isChecked ->
                onEvent(RoutingEvent.OnThemeToggle(isChecked))
            }
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(Sizing.routingScreenChatIconSize)
                    .clip(CircleShape)
                    .background(color = colors.buttonBorder),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_message_logo),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = colors.chatBubbleReceipt
                )
            }

            Spacer(modifier = Modifier.height(Spacing.spacing24))

            Text(
                text = stringResource(R.string.chat),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = Spacing.spacing28),
            verticalArrangement = Arrangement.spacedBy(Spacing.spacing12)
        ) {
            PrimaryButton(
                text = stringResource(R.string.log_in),
                onClick = {}
            )

            SecondaryButton(
                text = stringResource(R.string.registration),
                onClick = {
                    onEvent(RoutingEvent.OnNavigateRegistration)
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun RoutingScreenPreview() {
    ChatAppTheme {
        RoutingScreen()
    }
}