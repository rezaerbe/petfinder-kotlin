pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "PetFinder"
include(":app")
include(":core:common")
include(":core:preference")
include(":core:network")
include(":core:data")
include(":core:firebase")
include(":core:database")
include(":feature:login")
include(":feature:animal")
include(":feature:discussion")
include(":feature:task")
