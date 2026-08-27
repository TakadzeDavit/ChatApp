package com.space.ui.component.text_field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun BaseChatAppTextField(
    label: String,
    modifier: Modifier = Modifier,
    trailingIconSlot: @Composable (() -> Unit)? = null,
    inputFieldSlot: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = colors.textSecondary,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = Spacing.spacing08)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(Radius.textFieldRadius)
                .background(colors.inputTextField)
                .padding(horizontal = Spacing.spacing12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart,
            ) {
                inputFieldSlot()
            }

            trailingIconSlot?.invoke()
        }
    }
}