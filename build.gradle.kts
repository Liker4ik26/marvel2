plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.google.ksp).apply(false)
    alias(libs.plugins.kotlin.detekt).apply(true)
}

detekt{
    toolVersion = "1.22.0-RC2"
    source = files("src/main/java", "src/main/kotlin")
    parallel = true
    config = files("config.yml")
    buildUponDefaultConfig = true
    allRules = true
    disableDefaultRuleSets = false
    debug = true
    ignoreFailures = true
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")
    ignoredVariants = listOf("productionRelease")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}