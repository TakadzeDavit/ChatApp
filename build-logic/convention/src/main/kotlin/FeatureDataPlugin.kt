import com.space.chatapp.extensions.CORE_DATA_MODULE
import com.space.chatapp.extensions.CORE_DOMAIN_MODULE
import com.space.chatapp.extensions.featureName
import com.space.chatapp.extensions.implementationBundle
import com.space.chatapp.extensions.implementationLibrary
import com.space.chatapp.extensions.implementationModule
import com.space.chatapp.extensions.testImplementationBundle
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureDataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("chatapp.android.library")
            pluginManager.apply("io.insert-koin.compiler.plugin")

            dependencies {
             //   implementationLibrary(CORE_DOMAIN_MODULE)
                implementationModule(CORE_DATA_MODULE)
                implementationModule(":feature:${featureName()}:domain")
                testImplementationBundle("test")
                implementationBundle("koin")
            }
        }
    }
}