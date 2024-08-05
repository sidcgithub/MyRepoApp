plugins {
    alias(libs.plugins.myrepoapp.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.siddharthchordia.myrepoapp.core.domain"
}

dependencies {
    api(project(":core:data"))
    api(project(":core:model"))

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(project(":core:testing"))
}