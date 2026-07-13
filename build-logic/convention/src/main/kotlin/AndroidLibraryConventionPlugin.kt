import com.android.build.api.dsl.LibraryExtension
import com.uszkaisandor.bored.BuildConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        // AGP 9 has built-in Kotlin support; the kotlin-android plugin must NOT be applied.
        pluginManager.apply("com.android.library")

        extensions.configure<LibraryExtension> {
            compileSdk = BuildConfig.COMPILE_SDK
            defaultConfig {
                minSdk = BuildConfig.MIN_SDK
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }
}
