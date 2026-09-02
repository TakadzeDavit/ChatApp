plugins {
    alias(libs.plugins.chatapp.android.library)
}

android {
    namespace = "com.space.core.testing"

    buildFeatures {
        testFixtures.enable = true
    }
}

dependencies {
    testFixturesApi(libs.bundles.test)
}