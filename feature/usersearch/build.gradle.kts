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
    implementation(project(":core:di"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    api(project(":feature:repodetails"))
    testImplementation(project(":core:testing"))
    androidTestImplementation(libs.androidx.test.runner)
}