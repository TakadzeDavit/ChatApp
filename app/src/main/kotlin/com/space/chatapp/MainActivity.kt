package com.space.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.space.chatapp.navigation.ChatAppContainer
import com.space.core.data.local.database.dao.auth.UserDao
import com.space.core.data.local.database.dao.chat.ChatDao
import com.space.core.data.local.database.entity.auth.UserEntity
import com.space.core.data.local.database.entity.chat.ChatEntity
import com.space.feature.authentication.api.ChatFeatureKey
import com.space.ui.theme.ChatAppTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val userDao: UserDao by inject()
        val chatDao: ChatDao by inject()
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            userDao.insertUser(
                UserEntity("test-user-123", "Sandro", "adasd", "asdas")
            )
            chatDao.addChat(
                ChatEntity(
                    id = "chat1",
                    ownerId = "test-user-123",
                    contactName = "Daviti",
                    createdAt = 123123123,
                )
            )
            chatDao.addChat(
                ChatEntity(
                    id = "chat2",
                    ownerId = "test-user-123",
                    contactName = "Tornike",
                    createdAt = 123123123,
                )
            )
            chatDao.addChat(
                ChatEntity(
                    id = "chat3",
                    ownerId = "test-user-123",
                    contactName = "Nino",
                    createdAt = 123123123,
                )
            )
        }
        setContent {
            ChatAppTheme {
                ChatAppContainer(
                    startDestination = ChatFeatureKey
                )
            }
        }
    }
}