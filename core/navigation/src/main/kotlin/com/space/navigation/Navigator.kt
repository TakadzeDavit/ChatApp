package com.space.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack

class Navigator(val backStack: NavBackStack<NavKey>) {
    fun push(key: NavKey) {
        val index = backStack.indexOf(key)
        if (index != -1) {
            backStack.removeAt(index)
        }
        backStack.add(key)
    }

    fun pop() {
        if (backStack.size > 1) backStack.removeLastOrNull()
    }

    fun replaceLast(key: NavKey) {
        backStack[backStack.lastIndex] = key
    }
}

@Composable
fun rememberNavigator(initialKey: NavKey): Navigator {
    val backStack = rememberNavBackStack(initialKey)
    return remember { Navigator(backStack) }
}