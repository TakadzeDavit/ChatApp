plugins {
    alias(libs.plugins.chatapp.android.feature.presentation)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.authentication.presentation"
}

dependencies {
    implementation(projects.feature.authentication.domain)
}