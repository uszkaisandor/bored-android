plugins {
    id("bored.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.uszkaisandor.bored.leisure.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":leisure:domain"))

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.paging.common)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
