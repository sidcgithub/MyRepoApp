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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    ksp(libs.hilt.compiler)
    testImplementation(libs.androidx.navigation.testing)
    testImplementation(libs.junit)
    kspTest(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}