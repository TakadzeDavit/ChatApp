plugins {
    alias(libs.plugins.chatapp.android.application)
    alias(libs.plugins.chatapp.android.compose)
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
    testImplementation(libs.bundles.test)
}