package com.space.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Radius
import com.space.ui.theme.Sizing

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val shape = Radius.buttonRadius

    Box(
        modifier = modifier
            .height(Sizing.buttonHeight)
            .fillMaxWidth()
            .clip(shape)
            .background(colors.transparent)
            .border(
                border = BorderStroke(Sizing.buttonStrokeSize, color = colors.buttonBorder),
                shape = shape
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colors.buttonBorder
        )
    }
}