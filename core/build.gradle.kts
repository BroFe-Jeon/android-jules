plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") // KSP plugin
    id("dagger.hilt.android.plugin") // Hilt plugin
}

android {
    namespace = "com.example.core"
    compileSdk = 33 // Using a common version, adjust as needed

    defaultConfig {
        minSdk = 24 // Using a common version, adjust as needed
        targetSdk = 33 // Using a common version, adjust as needed

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
}

dependencies {
    // Hilt
    implementation("com.google.dagger:hilt-android:2.48") // Using a recent version
    ksp("com.google.dagger:hilt-compiler:2.48")

    // Room
    implementation("androidx.room:room-runtime:2.6.1") // Using a recent version
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Common stable version
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.15.0") // Using a recent version
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // Using a recent version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Standard AndroidX libraries
    implementation("androidx.core:core-ktx:1.12.0") // Using a recent version
    implementation("androidx.appcompat:appcompat:1.6.1") // Common stable version

    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
