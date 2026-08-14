import com.space.chatapp.extensions.implementationLibrary
import com.space.chatapp.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                val kotlinJvmPluginId = libs.findPlugin("jetbrains-kotlin-jvm").get().get().pluginId
                apply(kotlinJvmPluginId)
            }

            extensions.configure(JavaPluginExtension::class.java) {
                sourceCompatibility = org.gradle.api.JavaVersion.VERSION_21
                targetCompatibility = org.gradle.api.JavaVersion.VERSION_21
            }

            tasks.withType(KotlinCompile::class.java).configureEach {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }

            dependencies {
                implementationLibrary("kotlinx-coroutines-core")
            }
        }
    }
}