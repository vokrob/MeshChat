import io.github.vokrob.meshchat.buildlogic.configureMeshChatAndroid
import io.github.vokrob.meshchat.buildlogic.configureMeshChatQuality
import io.github.vokrob.meshchat.buildlogic.configureMeshChatUnitTests

plugins {
    id("com.android.application")
}

configureMeshChatAndroid()
configureMeshChatQuality()
configureMeshChatUnitTests()
