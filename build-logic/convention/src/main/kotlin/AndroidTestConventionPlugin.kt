import com.space.chatapp.extensions.testImplementationLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                testImplementationLibrary("junit")
                testImplementationLibrary("google.truth")
                testImplementationLibrary("mockk")
                testImplementationLibrary("kotlinx.coroutines.test")
                testImplementationLibrary("turbine")
            }
        }
    }
}