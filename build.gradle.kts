buildscript {
    dependencies {
        classpath(com.fatahrez.buildsrc.Dependencies.ProjectPlugins.hiltAndroidPlugin)
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version ("2.44.2") apply false
}