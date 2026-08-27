import com.space.chatapp.extensions.CORE_DOMAIN_MODULE
import com.space.chatapp.extensions.CORE_NAVIGATION_MODULE
import com.space.chatapp.extensions.CORE_PRESENTATION_MODULE
import com.space.chatapp.extensions.CORE_UI_MODULE
import com.space.chatapp.extensions.featureName
import com.space.chatapp.extensions.implementationBundle
import com.space.chatapp.extensions.implementationLibrary
import com.space.chatapp.extensions.implementationModule
import com.space.chatapp.extensions.testImplementationBundle
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeaturePresentationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("chatapp.android.library")
            pluginManager.apply("chatapp.android.compose")
            pluginManager.apply("io.insert-koin.compiler.plugin")

            dependencies {
                implementationModule(CORE_DOMAIN_MODULE)
                implementationModule(CORE_UI_MODULE)
                implementationModule(CORE_NAVIGATION_MODULE)
                implementationModule(CORE_PRESENTATION_MODULE)
                testImplementationBundle("test")
                implementationBundle("koin")
                implementationModule(":feature:${featureName()}:api")
//                implementationModule(":feature:${featureName()}:domain")
                implementationLibrary("androidx-lifecycle-viewmodel-ktx")
                implementationLibrary("androidx-lifecycle-viewmodel-compose")
                implementationLibrary("coil")
                implementationLibrary("kotlinx-serialization-json")
            }
        }
    }
}