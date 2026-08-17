package com.space.presentation

import org.koin.core.Koin
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

/**
 * Returns an existing scope by qualifier or creates a new one,
 * linking it to the [parent] scope if they are different.
 */

internal fun Koin.getOrCreateLinkedScope(
    qualifier: Qualifier,
    parent: Scope
): Scope = getScopeOrNull(qualifier.value)
    ?: createScope(qualifier.value, qualifier).also {
        if (it.id != parent.id) it.linkTo(parent)
    }