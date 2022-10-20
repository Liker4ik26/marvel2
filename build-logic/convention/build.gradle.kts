plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
gradlePlugin {
    plugins {
        register("build-config-plugin") {
            id = "marvel.build.config"
            implementationClass = "marvel.plugins.BuildConfigPlugin"
        }
        register("screen-module-plugin") {
            id = "marvel.module.screen"
            implementationClass = "marvel.plugins.ScreenModulePlugin"
        }
        register("compose-module-plugin") {
            id = "marvel.module.compose"
            implementationClass = "marvel.plugins.ComposeModulePlugin"
        }
    }
}
dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.google.ksp)
    compileOnly(gradleApi())
}