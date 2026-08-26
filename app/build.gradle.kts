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

    implementation(projects.feature.chat.data)
    implementation(projects.core.navigation)
    implementation(projects.core.presentation)
    implementation(projects.feature.authentication.api)
    implementation(projects.feature.authentication.presentation)
    implementation(projects.feature.authentication.data)
}