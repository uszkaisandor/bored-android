plugins {
    id("bored.android.library")
    id("bored.android.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.uszkaisandor.bored.settings.presentation"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:presentation:ui"))
    implementation(project(":core:presentation:designsystem"))

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.kotlinx.serialization.core)
}
