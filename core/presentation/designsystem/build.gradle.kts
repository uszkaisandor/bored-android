plugins {
    id("bored.android.library")
    id("bored.android.compose")
}

android {
    namespace = "com.uszkaisandor.bored.core.designsystem"
}

dependencies {
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.core.ktx)
    // MaterialColors.harmonize — align per-type accents with the dynamic scheme
    implementation(libs.google.material)
}
