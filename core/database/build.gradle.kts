plugins {
    id("bored.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.uszkaisandor.bored.core.database"
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)
}
