plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.room)
    alias(libs.plugins.kotlin.kapt)
}
android {
    namespace = "com.example.roomdatabase"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
    defaultConfig {
        applicationId = "com.example.roomdatabase"
        minSdk = 26
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

}

dependencies {

    annotationProcessor(libs.room.compiler)
    implementation(libs.room.runtime)


    // ViewModel and LiveData dependencies
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.livedata.ktx)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}