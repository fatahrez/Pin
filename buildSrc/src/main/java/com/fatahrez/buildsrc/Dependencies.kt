package com.fatahrez.buildsrc

object Versions {
    const val hiltAndroidPlugin = "2.42"
    const val retrofit = "2.9.0"
    const val okHttp = "4.10.0"
    const val hilt = "2.44.2"
    const val hiltWorker = "1.0.0"
    const val hiltCompiler = "1.0.0"
    const val hiltNavigation = "1.0.0"
    const val corektx = "1.9.0"
    const val lifecycle = "2.6.0"
    const val composeActivity = "1.6.1"
    const val compose = "1.4.0-beta01"
    const val material = "1.1.0-alpha08"
    const val jUnit = "4.13.2"
    const val extJUnit = "1.1.5"
    const val espresso = "3.5.1"
    const val uiTestJunit = "1.4.0-beta01"
    const val coil = "2.2.2"
    const val systemUIController = "0.26.0-alpha"
}

object Dependencies {

    object ProjectPlugins {
        val hiltAndroidPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroidPlugin}"
    }

    object Android {
        val nameSpace = "com.fatahrez.pin"
        val compileSdkVersion = 33
        val applicationId = "com.fatahrez.pin"
        val minSdk = 24
        val targetSdk = 33
        val versionCode = 1
        val versionName = "1.0"
        val kotlinComposeCompilerVersion = "1.4.2"
    }

    object NetworkLibs {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        val okHttpBOM ="com.squareup.okhttp3:okhttp-bom:${Versions.okHttp}"
        val okHttp = "com.squareup.okhttp3:okhttp"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
    }

    object HiltDI {
        val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        val hiltWorker = "androidx.hilt:hilt-work:${Versions.hiltWorker}"
        val HiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
        val daggerCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        val daggerHiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    }

    object UI {
        val coreKTX = "androidx.core:core-ktx:${Versions.corektx}"
        val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        val composeUI = "androidx.compose.ui:ui:${Versions.compose}"
        val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        val materialDesign= "androidx.compose.material3:material3:${Versions.material}"
    }

    object Testing {
        val jUnit = "junit:junit:${Versions.jUnit}"
        val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
        val espressoCore= "androidx.test.espresso:espresso-core:${Versions.espresso}"
        val uiTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.uiTestJunit}"
        val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }

    object ComposeUI {
        val coil = "io.coil-kt:coil:${Versions.coil}"
        val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
        val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUIController}"
    }

}