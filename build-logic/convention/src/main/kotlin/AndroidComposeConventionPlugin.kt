import com.android.build.api.dsl.CommonExtension
import com.uszkaisandor.bored.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Enables Jetpack Compose on a module that already applies an Android
 * application/library plugin. From Kotlin 2.0 the Compose compiler ships with
 * Kotlin and is turned on via the `org.jetbrains.kotlin.plugin.compose` plugin,
 * so there is no `kotlinCompilerExtensionVersion` to set. Uses the raw
 * [CommonExtension] to stay agnostic of the application/library extension type.
 */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        val extension = extensions.getByType(CommonExtension::class.java)
        extension.buildFeatures.compose = true

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
        }
    }
}
