import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

group = "com.space.chatapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "chatapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidApplication") {
            id = "chatapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidCompose") {
            id = "chatapp.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("featurePresentation") {
            id = "chatapp.android.feature.presentation"
            implementationClass = "FeaturePresentationPlugin"
        }
        register("featureData") {
            id = "chatapp.android.feature.data"
            implementationClass = "FeatureDataPlugin"
        }
        register("featureDomain") {
            id = "chatapp.feature.domain"
            implementationClass = "FeatureDomainPlugin"
        }
        register("androidKoin") {
            id = "chatapp.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }
    }
}