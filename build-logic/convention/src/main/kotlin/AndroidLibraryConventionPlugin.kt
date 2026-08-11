import com.android.build.api.dsl.LibraryExtension
import com.space.chatapp.extensions.implementationLibrary
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            extensions.configure<LibraryExtension> {
                compileSdk = 37

                defaultConfig {
                    minSdk = 26
                }

                buildFeatures {
                    buildConfig = true
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
            }

            dependencies {
                implementationLibrary("kotlinx-coroutines-core")
                implementationLibrary("kotlinx-coroutines-android")
                implementationLibrary("androidx-core-ktx")
                implementationLibrary("androidx-lifecycle-runtime-ktx")
            }
        }
    }
}