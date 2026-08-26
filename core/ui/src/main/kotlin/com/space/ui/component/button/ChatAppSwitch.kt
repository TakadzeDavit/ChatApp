package com.space.ui.component.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Sizing

@Composable
fun ChatAppSwitch(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    activeSwitchIcon: Int,
    inActiveIcon: Int,
    onCheckChange: (Boolean) -> Unit
) {
    Switch(
        checked = isDarkTheme,
        onCheckedChange = { onCheckChange(it) },
        modifier = modifier,
        thumbContent = {
            val iconRes = if (isDarkTheme) {
                activeSwitchIcon
            } else {
                inActiveIcon
            }

            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(Sizing.switchIconSize)
            )
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = colors.buttonBorder,
            checkedTrackColor = colors.inputTextField,
            uncheckedThumbColor = colors.buttonBorder,
            uncheckedTrackColor = colors.chatBubbleSender
        )
    )
}