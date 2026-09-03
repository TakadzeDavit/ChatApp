package com.space.presentation

import androidx.compose.runtime.Composable
import com.space.navigation.NavCommandBundle
import com.space.navigation.globalNavigator
import com.space.navigation.localNavigator
import kotlinx.coroutines.flow.Flow
import org.koin.core.parameter.ParametersDefinition
import kotlin.reflect.KClass

typealias VmClass<UIState, UIEvent> = KClass<out BaseVm<UIState, UIEvent>>

abstract class BaseScreen<UIState : UiState, UIEvent : UiEvent> {

    abstract val vmClass: VmClass<UIState, UIEvent>

    open val parameters: ParametersDefinition? = null

    @Composable
    abstract fun Content(state: UIState, onEvent: (UIEvent) -> Unit)
}

@Composable
internal fun NavCommands(navigationCommands: Flow<NavCommandBundle>) {
    val localNavigator = localNavigator()
    val globalNavigator = globalNavigator()

    androidx.compose.runtime.LaunchedEffect(Unit) {
        navigationCommands.collect {
            when {
                it.flowNavigationCommand != null ->
                    it.flowNavigationCommand!!.execute(localNavigator!!)

                it.featureNavigationCommand != null ->
                    it.featureNavigationCommand!!.execute(globalNavigator!!)
            }
        }
    }
}