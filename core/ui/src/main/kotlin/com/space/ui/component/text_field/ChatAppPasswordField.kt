package com.space.ui.component.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.space.ui.theme.ChatAppTheme.colors

@Composable
fun ChatAppPasswordField(
    state: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    BaseChatAppTextField(
        label = label,
        modifier = modifier,
        trailingIconSlot = {
            val iconRes = if (isPasswordVisible) {
                com.space.chatapp.core.ui.R.drawable.icon_visible_password
            } else {
                com.space.chatapp.core.ui.R.drawable.icon_not_visible_password
            }

            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                    focusManager.clearFocus()
                }
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = colors.textSecondary,
                )
            }
        }
    ) {
        BasicSecureTextField(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = colors.textPrimary, fontSize = 16.sp),
            textObfuscationMode = if (isPasswordVisible) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.Hidden
            }
        )
    }
}