plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = AppConfig.MINIMUM_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = AppConfig.testInstrumentationRunner

        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions.jvmTarget = "1.8"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    externalNativeBuild {
        ndkBuild {
            path("src/main/jni/Android.mk")
        }
    }
}

dependencies {

    coreLibraryDesugaring(Android.desugarJdkLibs)
    implementation(Android.coreKtx)
    implementation(Android.appCompact)
    implementation(Android.material)
    implementation(Android.constraintLayout)
    implementation(Android.viewpager2)
    implementation(Android.swipeRefreshLayout)
    implementation(Android.activityKtx)
    implementation(Android.fragmentKtx)
    implementation(Android.splashScreen)

    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    implementation(Coil.coil)
    implementation(Coil.gif)
    implementation(Coil.video)

    implementation(DataStore.dataStoreCore)
    implementation(DataStore.dataStorePreferences)

    implementation(Coroutines.coroutines)
    implementation(Coroutines.coroutinesAndroid)

    implementation(Lifecycle.viewModelKtx)
    implementation(Lifecycle.lifecycleKtx)

    implementation(Networking.retrofit)
    implementation(Networking.moshiRetrofitConverter)
    implementation(Networking.loggingInterceptor)

    implementation(Moshi.moshi)
    kapt(Moshi.moshiCodeGen)

    implementation(Navigation.navRuntime)
    implementation(Navigation.navFragment)
    implementation(Navigation.navUi)

    implementation(StartUp.startUp)
    implementation(Browser.browser)
    implementation(Timber.timber)

    debugImplementation(LeakCanary.leakCanary)

    testImplementation(Testing.junit)
    testImplementation(Testing.truth)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.junitAndroid)
    androidTestImplementation(Testing.espressoCore)
}
kapt {
    correctErrorTypes = true
}
