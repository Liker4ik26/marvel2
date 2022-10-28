package marvel.plugins

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class ComposeModulePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        with(project.plugins) {
            apply(BuildConfigPlugin::class.java)
            apply("com.google.devtools.ksp")
        }
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            with(androidExtension) {
                applyCompose(project)
                applyNavigation(project)
            }
        }
    }

    private fun BaseExtension.applyCompose(project: Project) {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion = libs.findVersion("compose").get().toString()

        project.dependencies {
            add("implementation", libs.findLibrary("androidx-core-ktx").get())

            add("implementation", libs.findLibrary("androidx-lifecycle-runtime").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())

            add("implementation", libs.findLibrary("androidx-activity").get())

            add("implementation", libs.findLibrary("androidx-compose-ui").get())
            add("implementation", libs.findLibrary("androidx-compose-material").get())
            add("implementation", libs.findLibrary("androidx-compose-icons").get())
            add("implementation", libs.findLibrary("androidx-compose-tooling-preview").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-tooling").get())

            add("implementation", libs.findLibrary("accompanist-insets").get())
            add("implementation", libs.findLibrary("accompanist-systemuicontroller").get())

            add("implementation", libs.findLibrary("coil-compose").get())
        }
    }

    private fun BaseExtension.applyNavigation(project: Project) {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        val kotlin = project.extensions.getByName("kotlin") as KotlinAndroidProjectExtension
        val ksp = project.extensions.getByName("ksp") as com.google.devtools.ksp.gradle.KspExtension
        when (this) {
            is LibraryExtension -> libraryVariants.all {
                kotlin.sourceSets.getByName(name).kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
            is AppExtension -> applicationVariants.all {
                kotlin.sourceSets.getByName(name).kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }

        ksp.apply {
            arg("compose-destinations.generateNavGraphs", "false")
        }

        project.dependencies {
            add("implementation", libs.findLibrary("compose-destinations").get())
            add("ksp", libs.findLibrary("compose-destinations-compiler").get())
        }
    }
}
