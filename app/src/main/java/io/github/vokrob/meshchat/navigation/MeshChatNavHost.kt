package io.github.vokrob.meshchat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.vokrob.meshchat.feature.chat.ChatScreen
import io.github.vokrob.meshchat.feature.chat.ChatsScreen
import io.github.vokrob.meshchat.feature.onboarding.OnboardingScreen
import io.github.vokrob.meshchat.feature.peers.PeersScreen
import io.github.vokrob.meshchat.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object OnboardingRoute

@Serializable
data object ChatsRoute

@Serializable
data object ChatRoute

@Serializable
data object PeersRoute

@Serializable
data object SettingsRoute

@Composable
fun MeshChatNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = OnboardingRoute,
        modifier = modifier,
    ) {
        composable<OnboardingRoute> {
            OnboardingScreen(
                onContinue = {
                    navController.navigate(ChatsRoute) {
                        popUpTo(OnboardingRoute) { inclusive = true }
                    }
                },
            )
        }
        composable<ChatsRoute> {
            ChatsScreen(onOpenChat = { navController.navigate(ChatRoute) })
        }
        composable<ChatRoute> {
            ChatScreen()
        }
        composable<PeersRoute> {
            PeersScreen()
        }
        composable<SettingsRoute> {
            SettingsScreen()
        }
    }
}
