package com.space.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.space.chatapp.navigation.MovieAppContainer
import com.space.feature.authentication.api.AuthFeatureKey
import com.space.ui.theme.ChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                MovieAppContainer(
                    startDestination = AuthFeatureKey
                )
            }
        }
    }
}