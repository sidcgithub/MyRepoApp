plugins {
    alias(libs.plugins.myrepoapp.android.library)
    alias(libs.plugins.myrepoapp.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.siddharthchordia.myrepoapp.core.data"
}

dependencies {
    api(project(":core:model"))
    api(project(":core:network"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
}