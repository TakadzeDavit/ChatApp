plugins {
    alias(libs.plugins.chatapp.android.feature.presentation)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.chat.presentation"
}
dependencies {
    implementation(projects.feature.chat.domain)
    implementation(projects.feature.chat.api)
}