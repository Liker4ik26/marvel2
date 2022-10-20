plugins {
    alias(libs.plugins.android.application)
    id("marvel.module.screen")
}

android {
    defaultConfig {
        applicationId = "com.marvel"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.savedstate)

    androidTestImplementation(libs.testRules)
    debugImplementation(libs.androidx.junit)
    androidTestImplementation(libs.testRunner)
    androidTestImplementation(libs.compose.test)
    androidTestImplementation(libs.compose.testManifest)

    implementation(libs.byteBuddy)
    implementation(libs.byteBuddyAndroid)

    implementation(libs.androidx.splashscreen)

    implementation(project(":screens:home"))
}