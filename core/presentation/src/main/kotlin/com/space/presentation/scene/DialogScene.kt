package com.space.presentation.scene

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.NavMetadataKey
import androidx.navigation3.runtime.get
import androidx.navigation3.runtime.metadata
import androidx.navigation3.scene.OverlayScene
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import com.space.ui.theme.ChatAppTheme.colors
import com.space.ui.theme.Padding
import com.space.ui.theme.Radius

internal data class DialogScene<T : Any>(
    override val key: T,
    override val previousEntries: List<NavEntry<T>>,
    override val overlaidEntries: List<NavEntry<T>>,
    private val entry: NavEntry<T>,
    private val properties: DialogProperties,
    private val onBack: () -> Unit

) : OverlayScene<T> {
    override val entries: List<NavEntry<T>> = listOf(entry)

    override val content: @Composable () -> Unit = {
        Dialog(
            onDismissRequest = onBack,
            properties = properties
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Padding.dialogPadding),
                shape = Radius.dialogRadius,
                color = colors.surface
            ) {
                Box(Modifier.padding(Padding.dialogContentPadding)) {
                    entry.Content()
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DialogSceneStrategy<T : Any> : SceneStrategy<T> {
    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        val lastEntry = entries.lastOrNull() ?: return null
        val properties = lastEntry.metadata[DialogKey] ?: return null

        return DialogScene(
            key = lastEntry.contentKey as T,
            previousEntries = entries.dropLast(1),
            overlaidEntries = entries.dropLast(1),
            entry = lastEntry,
            properties = properties,
            onBack = onBack
        )
    }

    companion object {
        fun dialog(
            properties: DialogProperties = DialogProperties()
        ) = metadata { put(DialogKey, properties) }

        private object DialogKey : NavMetadataKey<DialogProperties>
    }
}

@Composable
fun rememberDialogSceneStrategy(): DialogSceneStrategy<NavKey> =
    remember { DialogSceneStrategy() }
