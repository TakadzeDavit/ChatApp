package com.space.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Spacing
import com.space.ui.theme.TextSizing

@Composable
fun ChatAppTextDivider(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = colors.textSecondary
        )

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = Spacing.spacing12),
            fontSize = TextSizing.size14,
            color = colors.textSecondary
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = colors.textSecondary
        )
    }
}