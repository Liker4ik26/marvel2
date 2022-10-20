package marvel.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class ScreenModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project.plugins) {
            apply(BuildConfigPlugin::class.java)
            apply(ComposeModulePlugin::class.java)
        }
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            project.dependencies {
                add("implementation", project(":core"))
                add("implementation", project(":core-ui"))
            }
        }
    }
}
