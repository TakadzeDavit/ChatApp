package com.space.chatapp.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal const val IMPLEMENTATION = "implementation"
internal const val TEST_IMPLEMENTATION = "testImplementation"
internal const val DEBUG_IMPLEMENTATION = "debugImplementation"
internal const val CORE_UI_MODULE = ":core:ui"
internal const val CORE_DOMAIN_MODULE = ":core:domain"
internal const val CORE_DATA_MODULE = ":core:data"
internal const val CORE_PRESENTATION_MODULE = ":core:presentation"
internal const val CORE_NAVIGATION_MODULE = ":core:navigation"

// Get libs
internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Get feature package name
internal fun Project.featureName(): String {
    val segments = path.split(":").filter { it.isNotEmpty() }
    val featureIndex = segments.indexOf("feature")
    return segments.getOrNull(featureIndex + 1)
        ?: error("Feature name not found in path: $path")
}

// Dependency extensions
internal fun Project.implementationModule(module: String) {
    dependencies.add(IMPLEMENTATION, project(module))
}

internal fun Project.implementationLibrary(alias: String) {
    dependencies.add(IMPLEMENTATION, libs.findLibrary(alias).get())
}

internal fun Project.testImplementationLibrary(alias: String) {
    dependencies.add(TEST_IMPLEMENTATION, libs.findLibrary(alias).get())
}

internal fun Project.debugImplementationLibrary(alias: String) {
    dependencies.add(DEBUG_IMPLEMENTATION, libs.findLibrary(alias).get())
}