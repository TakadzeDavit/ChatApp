package com.space.navigation

import androidx.navigation3.runtime.NavKey

object FlowNavigationHelper : NavigationHelper<FlowNavigationKey>

object FeatureNavigationHelper : NavigationHelper<FeatureNavigationKey>

interface NavigationHelper<key : NavKey> {
    fun push(
        key: key
    ) = NavigationCommand.Push(key)

    fun pop() = NavigationCommand.Pop

    fun replaceLast(key: key) = NavigationCommand.ReplaceLast(key)
}