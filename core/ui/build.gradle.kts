plugins {
    alias(libs.plugins.myrepoapp.android.library)
    alias(libs.plugins.myrepoapp.android.library.compose)
}

android {
    namespace = "com.siddharthchordia.myrepoapp.core.ui"
}

dependencies {
    api(project(":core:model"))
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    api(libs.androidx.material3)
    implementation(libs.accompanist.systemuicontroller)
}