import groovy.json.JsonSlurper

// ══════════════════════════════════════════════════════
// Baca semua konfigurasi dari project-config.json
// ══════════════════════════════════════════════════════
val configFile = File(rootProject.rootDir, "project-config.json")
val cfg = JsonSlurper().parse(configFile) as Map<*, *>
val projectCfg = cfg["project"] as Map<*, *>
val androidCfg = cfg["android"] as Map<*, *>
val apiCfg = cfg["api"] as Map<*, *>

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = androidCfg["namespace"] as String
    compileSdk = (androidCfg["compileSdk"] as Number).toInt()

    defaultConfig {
        applicationId = androidCfg["applicationId"] as String
        minSdk = (androidCfg["minSdk"] as Number).toInt()
        targetSdk = (androidCfg["targetSdk"] as Number).toInt()
        versionCode = (androidCfg["versionCode"] as Number).toInt()
        versionName = androidCfg["versionName"] as String

        // Override app_name di strings.xml dari config
        resValue("string", "app_name", projectCfg["appName"] as String)

        // BuildConfig fields — bisa dipanggil dari kode Kotlin
        buildConfigField("String", "BASE_URL", "\"${apiCfg["baseUrl"]}\"")
        buildConfigField("Long", "TIMEOUT_SECONDS", "${apiCfg["timeoutSeconds"]}L")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
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
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    debugImplementation(libs.compose.ui.tooling)

    // Navigation
    implementation(libs.navigation.compose)

    // Networking
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Image Loading
    implementation(libs.coil.compose)
}
