package com.space.navigation

import androidx.navigation3.runtime.NavKey

interface NavigationCommand {
    fun execute(navigator: Navigator)

    data class Push(
        val key: NavKey
    ) : NavigationCommand {
        override fun execute(navigator: Navigator) {
            navigator.push(key)
        }
    }

    data object Pop : NavigationCommand {
        override fun execute(navigator: Navigator) {
            navigator.pop()
        }
    }
}

data class NavCommandBundle(
    val flowNavigationCommand: NavigationCommand? = null,
    val featureNavigationCommand: NavigationCommand? = null
)