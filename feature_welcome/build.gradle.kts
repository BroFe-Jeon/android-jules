plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android") version "2.48" apply false // Apply false, actual application is in app/build.gradle.kts
}

android {
    namespace = "com.example.feature_welcome"
    compileSdk = 33 // Or your project's standard compileSdk

    defaultConfig {
        minSdk = 24 // Or your project's standard minSdk
        targetSdk = 33 // Or your project's standard targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // Enable buildFeatures if you plan to use ViewBinding or DataBinding, though less common with Compose
    // buildFeatures {
    //    viewBinding = true
    // }
}

dependencies {
    implementation(project(":core"))

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // Use appropriate versions
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // AndroidX Lifecycle ViewModel (if ViewModels are part of this feature)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // Use appropriate versions

    // Standard AndroidX libraries (optional, but often useful)
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1") // If using AppCompat themes or components

    // Compose dependencies (if this feature has UI) - Add as needed
    // implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    // implementation("androidx.compose.ui:ui")
    // implementation("androidx.compose.material3:material3")
    // implementation("androidx.compose.ui:ui-tooling-preview")
    // debugImplementation("androidx.compose.ui:ui-tooling")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
