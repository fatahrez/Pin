import com.fatahrez.buildsrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.fatahrez.common"
    compileSdk = Dependencies.Android.compileSdkVersion

    defaultConfig {
        minSdk = Dependencies.Android.minSdk
        targetSdk = Dependencies.Android.targetSdk

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(
                    "proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2/api/v1/\"")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2/api/v1/\"")
        }
        register("local") {
            initWith(getByName("debug"))
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2/api/v1/\"")
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
        kotlinCompilerExtensionVersion = Dependencies.Android.kotlinComposeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature:feature_refresh_token"))

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