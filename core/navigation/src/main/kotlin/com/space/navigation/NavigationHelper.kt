package com.space.navigation

import androidx.navigation3.runtime.NavKey

object FlowNavigationHelper : NavigationHelper<FlowNavigationKey>

object FeatureNavigationHelper : NavigationHelper<FeatureNavigationKey>

interface NavigationHelper<key : NavKey> {
    fun push(
        key: NavKey
    ) = NavigationCommand.Push(key)

    fun pop() = NavigationCommand.Pop
}