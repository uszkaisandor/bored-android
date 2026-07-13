plugins {
    id("bored.android.library")
    id("bored.android.compose")
}

android {
    namespace = "com.uszkaisandor.bored.widget"
}

dependencies {
    implementation(project(":leisure:domain"))

    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(libs.kotlinx.coroutines.core)
}
