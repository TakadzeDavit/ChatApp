plugins {
    alias(libs.plugins.chatapp.android.library)
    alias(libs.plugins.koin.compiler)
    alias(libs.plugins.ksp)
}
android{
    namespace = "com.space.core.data"
}

dependencies {
    implementation(libs.bundles.room)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
    ksp(libs.room.compiler)
}