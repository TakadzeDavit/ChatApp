plugins {
    alias(libs.plugins.chatapp.android.feature.data)
}

android {
    namespace = "com.space.feature.auth.data"
}

dependencies {
    implementation(projects.feature.authentication.domain)
}