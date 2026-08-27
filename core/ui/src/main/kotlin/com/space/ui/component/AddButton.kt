package com.space.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.space.chatapp.core.ui.R
import com.space.ui.theme.Sizing


@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(Sizing.addIconSize),
            painter = painterResource(R.drawable.add),
            tint = Color.Unspecified,
            contentDescription = null
        )
    }
}