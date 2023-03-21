import com.fatahrez.buildsrc.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

kotlin {
    sourceSets {
        getByName("debug") {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        getByName("release") {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
        register("local") {
            kotlin.srcDir("build/generated/ksp/local/kotlin")
        }
    }
}

android {
    namespace = Dependencies.Android.nameSpace
    compileSdk = Dependencies.Android.compileSdkVersion

    defaultConfig {
        applicationId = Dependencies.Android.applicationId
        minSdk = Dependencies.Android.minSdk
        targetSdk = Dependencies.Android.targetSdk
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            applicationIdSuffix = ".debug"
        }
        register("local") {
            initWith(getByName("debug"))
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            applicationIdSuffix = ".local"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Android.kotlinComposeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:feature_auth"))
    implementation(project(":feature:feature_refresh_token"))

    //Retrofit
    implementation(Dependencies.NetworkLibs.retrofit)
    implementation(Dependencies.NetworkLibs.gsonConverter)

    //OkHttp BOM
    implementation(platform(Dependencies.NetworkLibs.okHttpBOM))
    implementation(Dependencies.NetworkLibs.okHttp)
    implementation(Dependencies.NetworkLibs.loggingInterceptor)

    //Hilt
    implementation(Dependencies.HiltDI.hiltAndroid)
    implementation(Dependencies.HiltDI.hiltWorker)
    kapt(Dependencies.HiltDI.HiltCompiler)
    kapt(Dependencies.HiltDI.daggerCompiler)
    kapt(Dependencies.HiltDI.daggerHiltAndroidCompiler)
    implementation(Dependencies.HiltDI.hiltNavigation)

    implementation(Dependencies.UI.coreKTX)
    implementation(Dependencies.UI.lifecycle)
    implementation(Dependencies.UI.composeActivity)
    implementation(Dependencies.UI.composeUI)
    implementation(Dependencies.UI.composeToolingPreview)
    implementation(Dependencies.UI.materialDesign)

    implementation(Dependencies.ComposeUI.systemUiController)

    // Navigation
    implementation(Dependencies.Navigation.composeDestinationsCore)
    ksp(Dependencies.Navigation.composeDestinationsKsp)
    implementation(Dependencies.Navigation.navigationCompose)

    testImplementation(Dependencies.Testing.jUnit)
    androidTestImplementation(Dependencies.Testing.extJUnit)
    androidTestImplementation(Dependencies.Testing.espressoCore)
    androidTestImplementation(Dependencies.Testing.uiTestJunit)
    debugImplementation(Dependencies.Testing.composeUiTooling)
    debugImplementation(Dependencies.Testing.composeUiTestManifest)
}