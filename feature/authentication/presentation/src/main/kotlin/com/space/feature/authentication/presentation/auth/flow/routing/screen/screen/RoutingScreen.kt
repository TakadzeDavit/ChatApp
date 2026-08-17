package com.space.feature.authentication.presentation.auth.flow.routing.screen.screen

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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.space.authentication.presentation.R
import com.space.ui.component.button.PrimaryButton
import com.space.ui.component.button.SecondaryButton
import com.space.ui.theme.ChatAppTheme
import com.space.ui.theme.ChatAppTheme.colors

@Composable
fun RoutingScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    isDarkTheme: Boolean = false,
    onThemeToggle: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        Switch(
            checked = false,
            onCheckedChange = onThemeToggle,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp),
            thumbContent = {
                Icon(
                    painter = painterResource(id = com.space.chatapp.core.ui.R.drawable.icon_light),
                    contentDescription = "Theme Toggle",
                    modifier = Modifier.size(16.dp)
                )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF7C67EE),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFDCD6FE)
            )
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF7C67EE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_message_logo),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = colors.chatBubbleReceipt
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

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
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PrimaryButton(
                text = stringResource(R.string.log_in),
                onClick = onLoginClick
            )

            SecondaryButton(
                text = stringResource(R.string.registration),
                onClick = onRegisterClick
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