@file:Suppress("unused", "ClassName", "MemberVisibilityCanPrivate")

package dependencies

import org.gradle.internal.impldep.org.bouncycastle.asn1.x500.style.RFC4519Style.name

object config {
    val applicationId = "com.tummy.app"
    val testRunner = "androidx.testing.runner.AndroidJUnitRunner"

    private val major = 1
    private val minor = 0
    private val patch = 0
    private val build = 0 // bump for builds, public betas, etc.

    val versionCode = major * 10000 + minor * 1000 + patch * 100 + build
    val versionName = "$major.$minor"
    val versionNameFull = "$versionName.$build"
}

object versions {
    val buildTools = "27.0.3"

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = compileSdk

    val kotlin = "1.3.21"
    val ktx = "1.1.0-alpha05"
    val androidX = "1.1.0-alpha04"
    val arch = "2.1.0-alpha03"
    val rxJava = "2.1.6"
    val rxAndroid = "2.1.0"
    val rxBinding = "2.2.0"
    val androidTest = "1.0.2"
    val gson = "2.8.5"
    val koin = "2.0.0-rc-2"
    val constraint = "1.1.3"
    val material = "1.1.0-alpha04"
    val support = "27.1.1"
    val gradle = "3.3.2"
    val lottie = "2.7.0"
    val googlePlay = "11.8.0"
    val retrofit = "2.4.0"
    val loggingInterceptor = "3.10.0"
    val glide = "4.9.0"
    val multidex = "2.0.1"
    val reactiveLocation = "1.0.5"
    val rxGps = "1.0.2"
    val epoxy = "3.3.0"
    val kotterknife = "0.1.1-SNAPSHOT"
    val rx_kotlin_2 = "2.2.0"
    val rx_relay = "2.1.0"
    val lifecycle = "2.0.0"
    val page_indicator_version = "1.0.1"
    val customTabs = "1.0.0"
    val butterknife = "10.1.0"
}

object plugin {
    object android {
        val gradle = "com.android.tools.build:gradle:${versions.gradle}"
    }

    object kotlin {
        val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        val runtime = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${versions.kotlin}"
    }

    object knife {
        val butter = "com.jakewharton:butterknife-gradle-plugin:${versions.butterknife}"
    }
}

object deps {
    val lottie = "com.airbnb.android:lottie:${versions.lottie}"
    val kotlin = "orviewModelg.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${versions.ktx}"
    val gson = "com.google.code.gson:gson:${versions.gson}"
    val glide = "com.github.bumptech.glide:glide:${versions.glide}"
    val multidex = "com.android.support:multidex:${versions.multidex}"
    val rxGps = "com.github.florent37:rxgps:${versions.rxGps}"
    val reactiveLocation = "com.patloew.rxlocation:rxlocation:${versions.reactiveLocation}"
    val kotterknife = "compile 'com.jakewharton:kotterknife:${versions.kotterknife}"
    val pageIndicator = "com.romandanylyk:pageindicatorview:${versions.page_indicator_version}"
    val customTabs = "com.android.support:customtabs:${versions.support}"

    object arch {
        val extensions = "androidx.lifecycle:lifecycle-extensions:${versions.arch}"
        val viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${versions.arch}"
    }

    object support {
        val appCompat = "androidx.appcompat:appcompat:${versions.androidX}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${versions.constraint}"
        val material = "com.google.android.material:material:${versions.material}"
        val design =  "com.android.support:design:${versions.support}"
        val recyclerView = "androidx.recyclerview:recyclerview:${versions.androidX}"
        val annotation = "androidx.annotation:annotation:${versions.androidX}"
        val livedata = "androidx.lifecycle:lifecycle-livedata:${versions.lifecycle}"
    }

    object koin {
        val android = "org.koin:koin-android:${versions.koin}"
        val viewModel = "org.koin:koin-androidx-viewmodel:${versions.koin}"
    }

    object epoxy {
        val dep = "com.airbnb.android:epoxy:${versions.epoxy}"
        val annotation = "com.airbnb.android:epoxy-processor:${versions.epoxy}"
    }

    object rx {
        val java = "io.reactivex.rxjava2:rxjava:${versions.rxJava}"
        val kotlin = "io.reactivex.rxjava2:rxkotlin:${versions.rx_kotlin_2}"
        val android = "io.reactivex.rxjava2:rxandroid:${versions.rxAndroid}"
        val binding = "com.jakewharton.rxbinding2:rxbinding-kotlin:${versions.rxBinding}"
        val relay = "com.jakewharton.rxrelay2:rxrelay:${versions.rx_relay}"
    }

    object googlePlay {
        val maps = "com.google.android.gms:play-services-maps:${versions.googlePlay}"
        val location = "com.google.android.gms:play-services-location:${versions.googlePlay}"
    }

    object retrofit {
        val retrofit = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
        val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${versions.retrofit}"
        val converterGson = "com.squareup.retrofit2:converter-gson:${versions.retrofit}"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${versions.loggingInterceptor}"
    }
}

object testing {
    val junit = "junit:junit:4.12"
    val rules = "com.android.support.test:rules:${versions.androidTest}"
    val runner = "com.android.support.test:runner:${versions.androidTest}"
}