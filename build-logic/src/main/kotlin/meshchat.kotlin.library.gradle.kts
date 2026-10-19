import io.github.vokrob.meshchat.buildlogic.configureMeshChatJvmLibrary
import io.github.vokrob.meshchat.buildlogic.configureMeshChatQuality
import io.github.vokrob.meshchat.buildlogic.configureMeshChatUnitTests

plugins {
    id("org.jetbrains.kotlin.jvm")
}

configureMeshChatJvmLibrary()
configureMeshChatQuality()
configureMeshChatUnitTests()
