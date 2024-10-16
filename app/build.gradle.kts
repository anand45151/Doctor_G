plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.doctorg"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.doctorg"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation ("com.google.android.material:material:1.3.0-alpha02")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.appcompat:appcompat:1.6.0")
    implementation ("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation ("com.github.bumptech.glide:glide:4.14.2") // 
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2") // For
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation ("com.squareup.picasso:picasso:2.8")


    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation ("androidx.core:core:1.6.0")
    implementation("com.airbnb.android:lottie:6.0.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:compose-theme-adapter:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}