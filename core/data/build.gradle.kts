plugins {
    alias(libs.plugins.chatapp.android.library)
    alias(libs.plugins.ksp)
}
android{
    namespace = "com.space.core.data"
}

dependencies {
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}