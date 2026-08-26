plugins {
    alias(libs.plugins.chatapp.android.application)
    alias(libs.plugins.chatapp.android.compose)
    alias(libs.plugins.koin.compiler)
}

android {
    namespace = "com.space.chatapp"

    defaultConfig {
        applicationId = "com.space.chatapp"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.bundles.koin)
    testImplementation(libs.bundles.test)

    // modules
    implementation(projects.core.ui)
    implementation(projects.core.data)
    implementation(projects.core.navigation)

    implementation(projects.feature.chat.data)
    implementation(projects.feature.chat.presentation)
    implementation(projects.feature.chat.api)
}