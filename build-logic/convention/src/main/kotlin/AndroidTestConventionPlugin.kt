import com.android.build.gradle.TestExtension
import com.siddharthchordia.myrepoapp.configureGradleManagedDevices
import com.siddharthchordia.myrepoapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.configure
import org.gradle.api.Project

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                configureGradleManagedDevices(this)
            }
        }
    }

}