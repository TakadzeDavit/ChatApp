package com.space.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.space.chatapp.core.ui.R
import com.space.ui.theme.ChatAppTheme
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Colors
import com.space.ui.theme.Padding
import com.space.ui.theme.Radius
import com.space.ui.theme.Sizing
import com.space.ui.theme.TextSizing

@Composable
fun ChatItem(
    title: String,
    lastMessageTime: String?,
    lastMessage: String?,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colors.background,
                shape = Radius.chatItemRadius
            )
            .height(Sizing.chatItemHeight)
            .padding(horizontal = Padding.chatItemPaddingH)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        LetterIcon(title = title)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    horizontal = Padding.chatItemPaddingH,
                    vertical = Padding.chatItemPaddingV
                )
                .weight(1f),
        ) {
            Text(text = title, color = colors.textPrimary)
            Text(
                text = lastMessage ?: stringResource(R.string.empty_last_message),
                color = colors.textPrimary.copy(alpha = 0.5f),
                fontSize = TextSizing.size12,
                lineHeight = TextSizing.size12,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = lastMessageTime ?: "",
            color = colors.textPrimary,
            fontSize = TextSizing.size10,
            modifier = Modifier
                .align(Alignment.Top)
                .padding(horizontal = Padding.chatItemPaddingH)
        )
    }
}

@Composable
fun LetterIcon(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = getRandomColor(),
    textColor: Color = colors.onSurface,
) {
    val initial = title.firstOrNull() ?: "?"

    Box(
        modifier = modifier
            .size(Sizing.letterIconSize)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial.toString(),
            color = textColor,
            fontSize = TextSizing.size18,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
@Preview
private fun LetterIconPreview() {
    ChatAppTheme {
        LetterIcon("სანდრო")
    }
}

private fun getRandomColor(): Color {
    val colorPool = arrayOf(
        Colors.PurpleDefault,
        Colors.Dark01MidnightBlue,
        Colors.Yellow,
        Colors.PurpleLight
    )
    return colorPool.random()
}

@Composable
fun SwipeToDeleteChatItem(
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
        positionalThreshold = { totalDistance -> totalDistance * 0.5f }
    )
    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            onDelete()
        }
    }
    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromEndToStart = true,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = Padding.swipeIconPadding),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(R.drawable.trash),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    ) {
        content()
    }
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
private fun ChatItemPreview() {
    ChatAppTheme {
        ChatItem(
            "სანდრო",
            "გუშინ",
            "როგორ ხარ როგორ" +
                    " იმოგზაურე რამე ხომ არ დაგრჩა სამსახურში არ დააგვიანო  ?"
        ) { }
    }
}