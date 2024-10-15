plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.mateus.mytasks"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mateus.mytasks"
        minSdk = 26
        targetSdk = 34
        versionCode = 1000
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.preference.ktx)
    implementation(libs.swipe.refresh.layout)
    implementation(libs.firebase.auth.ktx)
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.android.gms:play-services-auth:20.7.0")

}