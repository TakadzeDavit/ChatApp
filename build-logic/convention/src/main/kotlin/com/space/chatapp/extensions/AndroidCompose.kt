package com.space.chatapp.extensions

import com.android.build.api.dsl.CommonExtension
import com.space.chatapp.extensions.debugImplementationLibrary
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    baseExtension: CommonExtension
) {
    baseExtension.apply {
        buildFeatures.compose = true
    }

    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    dependencies {
        // Compose BOM
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))

        // UI
        implementationLibrary("androidx-activity-compose")
        implementationLibrary("androidx-compose-material3")
        implementationLibrary("androidx-compose-ui")
        implementationLibrary("androidx-compose-ui-graphics")
        implementationLibrary("androidx-compose-ui-tooling-preview")
        implementationLibrary("androidx-lifecycle-runtime-ktx")
        implementationLibrary("androidx-core-ktx")

        // tools
        debugImplementationLibrary("androidx-compose-ui-tooling")

        // navigation3
        implementationLibrary("androidx-navigation3-runtime")
        implementationLibrary("androidx-navigation3-ui")
        implementationLibrary("androidx-lifecycle-viewmodel-navigation3")
    }
}