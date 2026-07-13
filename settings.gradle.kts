pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Bored"
include(":app")
include(":core:domain")
include(":core:database")
include(":core:data")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":leisure:domain")
include(":leisure:data")
include(":leisure:presentation")
include(":settings:presentation")
include(":widget")
