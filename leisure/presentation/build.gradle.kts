plugins {
    id("bored.android.library")
    id("bored.android.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.uszkaisandor.bored.leisure.presentation"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":leisure:domain"))
    implementation(project(":core:presentation:ui"))
    implementation(project(":core:presentation:designsystem"))

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.graphics.shapes)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.kotlinx.serialization.core)

    implementation(libs.lottie.compose)
    implementation(libs.androidx.paging.compose)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
