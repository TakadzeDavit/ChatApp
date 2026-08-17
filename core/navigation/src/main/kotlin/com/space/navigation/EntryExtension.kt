package com.space.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import org.koin.compose.LocalKoinScopeContext
import org.koin.compose.scope.KoinScope
import org.koin.core.Koin
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * Registers a navigation entry for [T] that creates and provides [S] scope
 * linked to the current parent scope.
 */

@OptIn(KoinExperimentalAPI::class, KoinInternalApi::class)
inline fun <reified T : NavKey, reified S : Any> EntryProviderScope<NavKey>.scopedEntry(
    noinline content: @Composable () -> Unit
) {
    entry<T> {
        val parentScope = LocalKoinScopeContext.current.getValue()
        val scopeQualifier = named<S>()

        KoinScope(
            scopeDefinition = {
                getOrCreateLinkedScope(
                    qualifier = scopeQualifier,
                    parent = parentScope
                )
            }
        ) {
            content()
        }
    }
}


/**
 * Returns an existing scope by qualifier or creates a new one,
 * linking it to the [parent] scope if they are different.
 */

fun Koin.getOrCreateLinkedScope(
    qualifier: Qualifier,
    parent: Scope
): Scope = getScopeOrNull(qualifier.value)
    ?: createScope(qualifier.value, qualifier).also {
        if (it.id != parent.id) it.linkTo(parent)
    }