import com.fatahrez.buildsrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.fatahrez.feature_auth"
    compileSdk = Dependencies.Android.compileSdkVersion

    defaultConfig {
        minSdk = Dependencies.Android.minSdk
        targetSdk = Dependencies.Android.targetSdk

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile(
                        "proguard-android-optimize.txt"
                    ),
                    "proguard-rules.pro"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":common"))

    implementation(Dependencies.UI.coreKTX)
    implementation(Dependencies.UI.materialDesign)

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

    implementation(Dependencies.UI.lifecycle)
    implementation(Dependencies.UI.composeActivity)
    implementation(Dependencies.UI.composeUI)
    implementation(Dependencies.UI.composeToolingPreview)

    testImplementation(Dependencies.Testing.jUnit)
    androidTestImplementation(Dependencies.Testing.extJUnit)
    androidTestImplementation(Dependencies.Testing.espressoCore)
    androidTestImplementation(Dependencies.Testing.uiTestJunit)
    debugImplementation(Dependencies.Testing.composeUiTooling)
    debugImplementation(Dependencies.Testing.composeUiTestManifest)
}