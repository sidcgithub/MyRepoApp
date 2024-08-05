plugins {
    alias(libs.plugins.myrepoapp.android.library)
    alias(libs.plugins.myrepoapp.hilt)
}

android {
    namespace = "com.siddharthchordia.myrepoapp.core.di"
}

dependencies {
    api(project(":core:model"))
}