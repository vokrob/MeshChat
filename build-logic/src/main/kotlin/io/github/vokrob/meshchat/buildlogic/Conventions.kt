package io.github.vokrob.meshchat.buildlogic

import com.android.build.api.dsl.CommonExtension
import dev.detekt.gradle.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

private val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

private fun Project.catalogVersion(alias: String): String =
    versionCatalog.findVersion(alias).get().requiredVersion

private fun Project.catalogLibrary(alias: String): MinimalExternalModuleDependency =
    versionCatalog.findLibrary(alias).get().get()

internal fun Project.configureMeshChatQuality() {
    pluginManager.apply("org.jlleitschuh.gradle.ktlint")
    pluginManager.apply("dev.detekt")

    extensions.configure<KtlintExtension> {
        version.set(catalogVersion("ktlintCli"))
    }
    extensions.configure<DetektExtension> {
        buildUponDefaultConfig.set(true)
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
    }
}

internal fun Project.configureMeshChatAndroid() {
    extensions.configure<CommonExtension> {
        compileSdk {
            version =
                release(37) {
                    minorApiLevel = 1
                }
        }
        defaultConfig.minSdk = 28
        compileOptions.sourceCompatibility = JavaVersion.VERSION_11
        compileOptions.targetCompatibility = JavaVersion.VERSION_11
    }
}

internal fun Project.configureMeshChatCompose() {
    extensions.configure<CommonExtension> {
        buildFeatures.compose = true
    }
    dependencies {
        add("implementation", platform(catalogLibrary("androidx-compose-bom")))
        add("implementation", catalogLibrary("androidx-compose-foundation"))
        add("implementation", catalogLibrary("androidx-compose-material3"))
        add("implementation", catalogLibrary("androidx-compose-ui"))
    }
}

internal fun Project.configureMeshChatUnitTests() {
    dependencies {
        add("testImplementation", catalogLibrary("junit"))
    }
}

internal fun Project.configureMeshChatJvmLibrary() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}
