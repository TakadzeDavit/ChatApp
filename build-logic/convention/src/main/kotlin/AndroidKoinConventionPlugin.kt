import com.space.chatapp.extensions.implementationBundle
import com.space.chatapp.extensions.implementationLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.insert-koin.compiler.plugin")

            dependencies {
                implementationBundle("koin")
            }
        }
    }
}