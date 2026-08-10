plugins {
    alias(libs.plugins.chatapp.android.application)
    alias(libs.plugins.chatapp.android.compose)
    alias(libs.plugins.chatapp.android.test)
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
    implementation(projects.core.ui)
}