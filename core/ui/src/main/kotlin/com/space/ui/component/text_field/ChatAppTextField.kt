package com.space.ui.component.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.TextSizing

@Composable
fun ChatAppTextField(
    state: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    BaseChatAppTextField(
        label = label,
        modifier = modifier,
    ) {
        BasicTextField(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = colors.textPrimary, fontSize = TextSizing.size16),
            keyboardOptions = keyboardOptions,
            cursorBrush = SolidColor(colors.textPrimary)
        )
    }
}