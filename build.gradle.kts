plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false // Thêm dòng này
    alias(libs.plugins.kotlin.kapt) apply false // Thêm dòng này
}
