plugins {
    id("bored.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.uszkaisandor.bored.core.data"
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(libs.kotlinx.serialization.json)
}
