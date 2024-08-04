plugins {
    alias(libs.plugins.myrepoapp.android.feature)
    alias(libs.plugins.myrepoapp.android.library.compose)
}

android {
    namespace = "com.siddharthchordia.myrepoapp.feature.usersearch"
}

dependencies {
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.test.runner)
}