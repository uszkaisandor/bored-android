plugins {
    id("bored.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.uszkaisandor.bored.core.data"
}

dependencies {
    implementation(project(":core:domain"))

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
}
