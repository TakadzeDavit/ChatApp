package com.space.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.space.chatapp.core.ui.R
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Sizing
import com.space.ui.theme.TextSizing

@Composable
fun EmptyList(
    modifier: Modifier,
    primaryText: String,
    secondaryText: String
) {
    Column(
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.empty_list),
            modifier = Modifier
                .size(Sizing.emptyIconSize)
                .align(Alignment.CenterHorizontally),
            contentDescription = null
        )
        Spacer(Modifier.height(Sizing.sizing20))
        Text(
            text = primaryText,
            fontSize = TextSizing.size26,
            color = colors.textPrimary,
        )
        Spacer(Modifier.height(Sizing.sizing12))
        Text(
            text = secondaryText,
            fontSize = TextSizing.size18,
            color = colors.textSecondary,
        )
    }
}