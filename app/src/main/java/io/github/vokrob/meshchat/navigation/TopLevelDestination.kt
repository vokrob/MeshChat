package io.github.vokrob.meshchat.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.vokrob.meshchat.R

enum class TopLevelDestination(
    val route: Any,
    @param:StringRes val labelResId: Int,
    val icon: ImageVector,
) {
    CHATS(ChatsRoute, R.string.nav_chats, Icons.Filled.Email),
    PEERS(PeersRoute, R.string.nav_peers, Icons.Filled.Person),
    SETTINGS(SettingsRoute, R.string.nav_settings, Icons.Filled.Settings),
}
