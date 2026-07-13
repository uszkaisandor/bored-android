plugins {
    id("bored.kotlin.library")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    // core:domain is `api` because Outcome/DomainError appear in this module's public contract.
    api(project(":core:domain"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common)
    implementation(libs.kotlinx.serialization.json)
}
