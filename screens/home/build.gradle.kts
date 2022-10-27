plugins {
    alias(libs.plugins.android.library)
    id("marvel.module.screen")
}

android {
    namespace = "com.marvel2.home"
}

dependencies{
    implementation("dev.chrisbanes.snapper:snapper:0.3.0")
    implementation("com.google.accompanist:accompanist-pager:0.12.0")

}