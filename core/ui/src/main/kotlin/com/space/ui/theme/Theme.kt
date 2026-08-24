package com.space.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ChatAppColors(
    val chatBubbleReceipt: Color,
    val chatBubbleSender: Color,
    val textInputBorder: Color,
    val divider: Color,
    val surface: Color,
    val transparent: Color,
    val buttonBorder: Color,
    val inputTextField: Color,
    val background: Color,
    val onSurface: Color,
    val onTransparent: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val error: Color
)

val LightChatColors = ChatAppColors(
    background = Colors.Neutral06White,
    chatBubbleSender = Colors.PurpleLight,
    chatBubbleReceipt = Colors.Neutral05LightestGrey,
    textInputBorder = Colors.PurpleDefault,
    divider = Colors.dividerPrimary,
    surface = Colors.PurpleDefault,
    transparent = Color.Transparent,
    buttonBorder = Colors.PurpleDefault,
    inputTextField = Colors.Neutral05LightestGrey,
    onSurface = Colors.Neutral06White,
    onTransparent = Colors.PurpleDefault,
    textPrimary = Colors.Neutral01GreatDarkGrey,
    textSecondary = Colors.Neutral02DarkGrey,
    error = Colors.ErrorLabel
)

val DarkChatColors = ChatAppColors(
    background = Colors.Dark01MidnightBlue,
    chatBubbleSender = Colors.PurpleLight,
    chatBubbleReceipt = Colors.Neutral05LightestGrey,
    textInputBorder = Colors.PurpleDefault,
    divider = Colors.dividerPrimary,
    surface = Colors.PurpleLight,
    buttonBorder = Colors.PurpleDefault,
    inputTextField = Colors.Dark02Blue,
    transparent = Color.Transparent,
    onSurface = Colors.Neutral06White,
    onTransparent = Colors.PurpleDefault,
    textPrimary = Colors.Neutral06White,
    textSecondary = Colors.Neutral02DarkGrey,
    error = Colors.ErrorLabel
)


val LocalChatAppColors = staticCompositionLocalOf<ChatAppColors> {
    error("No chat app colors provided")
}

object ChatAppTheme {
    val colors: ChatAppColors @Composable get() = LocalChatAppColors.current
}

@Composable
fun ChatAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val chatColors = if (darkTheme) {
        DarkChatColors
    } else {
        LightChatColors
    }
    CompositionLocalProvider(LocalChatAppColors provides chatColors) {
        MaterialTheme(content = content)
    }
}
