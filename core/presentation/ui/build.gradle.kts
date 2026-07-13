plugins {
    id("bored.android.library")
    id("bored.android.compose")
}

android {
    namespace = "com.uszkaisandor.bored.core.ui"
}

dependencies {
    implementation(project(":core:presentation:designsystem"))

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.kotlinx.coroutines.core)
}
