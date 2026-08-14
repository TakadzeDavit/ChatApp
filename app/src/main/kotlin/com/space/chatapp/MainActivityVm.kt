package com.space.chatapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.chatapp.contract.MainActivityState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivityVm : ViewModel() {
    private val _state : MutableStateFlow<MainActivityState> = MutableStateFlow(MainActivityState())
    val state = _state.asStateFlow()

    init {
        launchSplash()
    }

    private fun launchSplash() {
        viewModelScope.launch {
            delay(1500.milliseconds)
            _state.update { it.copy(isLoading = false) }
        }
    }
}