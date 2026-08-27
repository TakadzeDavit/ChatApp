plugins {
    alias(libs.plugins.chatapp.android.library)
}

android {
    namespace = "com.space.core.testing"
}

dependencies {
    api(libs.bundles.test)
}