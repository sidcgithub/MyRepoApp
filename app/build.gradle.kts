plugins {
    alias(libs.plugins.myrepoapp.android.application)
    alias(libs.plugins.myrepoapp.android.application.compose)
    alias(libs.plugins.myrepoapp.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.siddharthchordia.myrepoapp"

    defaultConfig {
        applicationId = "com.siddharthchordia.myrepoapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.siddharthchordia.myrepoapp.CustomJunitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(project(":core:network"))
    implementation(libs.androidx.test.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    api(project(":core:ui"))
    implementation(project(":feature:navigationshell"))
    implementation(project(":feature:usersearch"))
    ksp(libs.hilt.compiler)
    testImplementation(libs.androidx.navigation.testing)
    testImplementation(libs.junit)
    kspTest(libs.hilt.compiler)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

}