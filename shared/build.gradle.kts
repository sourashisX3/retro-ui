import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.svgToCompose)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    jvm()
    
    js {
        browser()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
    
    android {
       namespace = "com.funapp.retroui.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
       withDeviceTestBuilder {
           sourceSetTreeName = "test"
       }.configure {
           instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlinx.serializationJson)
            implementation(libs.navigationCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}

svgToCompose {
    processor {
        val retroIcons by creating {
            from(layout.projectDirectory.dir("../icons/pixelarticons"))
            destinationPackage("com.funapp.retroui.core.ui.icons")
            optimize(false)
            icons {
                minify()
                noPreview()
                receiverType("com.funapp.retroui.core.ui.icons.RetroIcons")
                mapIconNameTo { name ->
                    when (name.lowercase()) {
                        "star" -> "Star"
                        "play" -> "PlayArrow"
                        "user" -> "Person"
                        "check" -> "Check"
                        "heart" -> "Favorite"
                        "robot-face" -> "Face"
                        "thumbs-up" -> "ThumbUp"
                        "home" -> "Home"
                        "search" -> "Search"
                        "lock" -> "Lock"
                        "mail" -> "MailOutline"
                        "avatar-circle" -> "AccountCircle"
                        "settings-cog" -> "Settings"
                        "pencil" -> "Edit"
                        "tools" -> "Build"
                        "magic-edit" -> "Create"
                        "logout" -> "ExitToApp"
                        "plus-box" -> "AddCircle"
                        "square-alert" -> "Warning"
                        "plus" -> "Add"
                        "circle-info" -> "Info"
                        "bell" -> "Notifications"
                        "arrow-left" -> "ArrowBack"
                        "gamepad" -> "Gamepad"
                        "trophy" -> "Trophy"
                        "shield" -> "Shield"
                        "sword" -> "Sword"
                        "crown" -> "Crown"
                        "card" -> "Card"
                        "joystick" -> "Joystick"
                        "flag" -> "Flag"
                        "eye" -> "Eye"
                        "eye-off" -> "EyeOff"
                        "smile" -> "Smile"
                        else -> name
                    }
                }
            }
        }
    }
}