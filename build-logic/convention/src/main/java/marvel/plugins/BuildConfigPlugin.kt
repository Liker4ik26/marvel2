package marvel.plugins

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import marvel.ANDROID_COMPILE_SDK_VERSION
import marvel.ANDROID_MIN_SDK_VERSION
import marvel.ANDROID_TARGET_SDK_VERSION
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class BuildConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project.plugins) {
            apply("org.jetbrains.kotlin.android")
            apply("kotlin-parcelize")
        }
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            with(androidExtension) {
                applyAndroidSettings(project)
                applyBuildTypes()
                applyJava8(project)
                applyBaseDependencies(project)
            }
        }
    }

    private fun BaseExtension.applyAndroidSettings(project: Project) {
        compileSdkVersion(project.ANDROID_COMPILE_SDK_VERSION)
        defaultConfig {
            minSdk = project.ANDROID_MIN_SDK_VERSION
            targetSdk = project.ANDROID_TARGET_SDK_VERSION
        }
    }

    private fun BaseExtension.applyBuildTypes() {
        flavorDimensions("default")
        productFlavors {
            create("dev") {
                dimension = "default"
        }
    }
    }

    private fun BaseExtension.applyProguardSettings(project: Project) {
        val proguardFolder = "${project.projectDir}/proguard"
        val coroutines = "$proguardFolder/coroutines.pro"
        val okhttp = "$proguardFolder/okhttp3.pro"
        val okio = "$proguardFolder/okio.pro"
        val retrofit2 = "$proguardFolder/retrofit2.pro"
        when (this) {
            is LibraryExtension -> defaultConfig {
                consumerProguardFiles(
                    coroutines,
                    okhttp,
                    okio,
                    retrofit2
                )
            }
            is AppExtension -> buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    signingConfig = signingConfigs.getByName("internal")
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        coroutines,
                        okhttp,
                        okio,
                        retrofit2
                    )
                }
                getByName("autotest") {
                    signingConfig = signingConfigs.getByName("internal")
                    isMinifyEnabled = false
                    isShrinkResources = false
                    setIsDefault(false)
                }
            }
        }
    }

    private fun BaseExtension.applyJava8(project: Project) {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        project.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    private fun applyBaseDependencies(project: Project) {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        project.dependencies {
        }
    }

}