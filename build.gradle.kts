plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.2" apply false
    id("com.google.gms.google-services") version "4.3.14" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}