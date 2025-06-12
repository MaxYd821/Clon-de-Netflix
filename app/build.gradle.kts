plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    //alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.clondenetflix"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.clondenetflix"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation ("com.google.android.material:material:1.11.0")
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-storage-ktx")

    implementation ("com.github.bumptech.glide:glide:4.15.1")
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
    implementation ("com.google.android.exoplayer:exoplayer:2.19.1")

}