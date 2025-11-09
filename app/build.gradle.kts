plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.0.21"
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "br.com.moscatech.dumbpassword"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.moscatech.dumbpassword"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "app_name", "Dumb Password")
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            resValue("string", "app_name", "[debug] Dumb Password")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
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
    implementation("androidx.cardview:cardview:1.0.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-android-compiler:2.57.2")

    // Navigation
    implementation("androidx.navigation:navigation-fragment:2.9.5")
    implementation("androidx.navigation:navigation-ui:2.9.5")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.9.5")
    androidTestImplementation("androidx.navigation:navigation-testing:2.9.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // Preferences Data Store
    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // Room
    implementation("androidx.room:room-runtime:2.8.2")
    ksp("androidx.room:room-compiler:2.8.2")
    implementation("androidx.room:room-ktx:2.8.2")
    testImplementation("androidx.room:room-testing:2.8.2")
    implementation("androidx.room:room-paging:2.8.2")

    // Gson
    implementation("com.google.code.gson:gson:2.13.2")
}