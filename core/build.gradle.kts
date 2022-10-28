plugins {
    alias(libs.plugins.android.library)
    id("marvel.module.dagger")
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.savedstate)
    implementation(libs.compose.test)
}
