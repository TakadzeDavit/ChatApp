plugins {
    alias(libs.plugins.chatapp.android.feature.presentation)
}

android {
    namespace = "com.space.chat.presentation"
}
dependencies {
    implementation(projects.feature.chat.api)
}