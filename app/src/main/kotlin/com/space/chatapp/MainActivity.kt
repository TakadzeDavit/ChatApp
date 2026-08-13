package com.space.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.ui.theme.ChatAppTheme
import com.space.ui.theme.ChatAppTheme.colors
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.state.value.isLoading
        }

        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background))
            }
        }
    }
}