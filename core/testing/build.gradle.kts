plugins {
    alias(libs.plugins.myrepoapp.android.library)
    alias(libs.plugins.myrepoapp.hilt)
}
android {
    namespace = "com.siddharthchordia.myrepoapp.core.testing"
}

dependencies {
    api(libs.kotlinx.coroutines.test)
    api(libs.mockk)
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
}