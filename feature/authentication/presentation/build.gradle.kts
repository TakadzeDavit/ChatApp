plugins {
    alias(libs.plugins.chatapp.android.feature.presentation)
}

android {
    namespace = "com.space.authentication.presentation"
}
dependencies {
    implementation(projects.feature.chat.api)
    implementation(projects.feature.authentication.api)
}