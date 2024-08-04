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

    testImplementation(project(":core:testing"))
}