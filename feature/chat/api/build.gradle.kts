plugins {
    alias(libs.plugins.chatapp.android.library)
    alias(libs.plugins.chatapp.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.feature.chat.api"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(projects.core.navigation)
}