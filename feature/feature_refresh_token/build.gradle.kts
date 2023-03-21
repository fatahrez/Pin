import com.fatahrez.buildsrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.fatahrez.feature_refresh_token"
    compileSdk = Dependencies.Android.compileSdkVersion

    defaultConfig {
        minSdk = Dependencies.Android.minSdk
        targetSdk = Dependencies.Android.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(Dependencies.UI.coreKTX)

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
}