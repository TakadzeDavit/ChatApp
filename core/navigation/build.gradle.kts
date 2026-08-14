plugins {
    alias(libs.plugins.chatapp.android.library)
    alias(libs.plugins.chatapp.android.compose)
    alias(libs.plugins.koin.compiler)
}

android {
    namespace = "com.space.core.navigation"
}

dependencies {
    implementation(libs.bundles.koin)
}