package com.space.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.space.ui.theme.ChatAppTheme
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Colors

@Composable
fun ChatItem(
    title: String,
    lastMessageTime: String?,
    lastMessage: String?,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 6.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        LetterIcon(title = title)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .weight(1f),
        ) {
            Text(text = title)
            Text(
                text = lastMessage ?: "",
                fontSize = 12.sp,
                lineHeight = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = lastMessageTime ?: "",
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.Top)
                .padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun LetterIcon(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Colors.Dark02Blue,
    textColor: Color = colors.onSurface,
) {
    val initial = title.firstOrNull() ?: "?"

    Box(
        modifier = modifier
            .size(40.dp)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial.toString(),
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

//@Composable
//@Preview
//private fun LetterIconPreview() {
//    ChatAppTheme {
//        LetterIcon("სანდრო")
//    }
//}

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