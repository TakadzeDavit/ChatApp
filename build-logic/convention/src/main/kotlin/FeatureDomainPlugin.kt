import com.space.chatapp.extensions.CORE_DOMAIN_MODULE
import com.space.chatapp.extensions.implementationLibrary
import com.space.chatapp.extensions.implementationModule
import com.space.chatapp.extensions.testImplementationBundle
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureDomainPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("chatapp.jvm.library")

            dependencies {
                implementationModule(CORE_DOMAIN_MODULE)
                implementationLibrary("kotlinx-coroutines-core")
                testImplementationBundle("test")
            }
        }
    }
}