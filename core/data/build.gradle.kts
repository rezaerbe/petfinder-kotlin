plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.erbeandroid.petfinder.core.data"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}

dependencies {

    implementation(project(":core:network"))
    implementation(project(":core:common"))

    implementation("androidx.paging:paging-runtime:3.1.1")

    implementation("com.google.dagger:hilt-android:2.43.2")
    kapt("com.google.dagger:hilt-android-compiler:2.43.2")
}