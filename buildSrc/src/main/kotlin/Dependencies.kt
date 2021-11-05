object Android {
    const val appCompact = "androidx.appcompat:appcompat:1.3.1"
    const val coreKtx = "androidx.core:core-ktx:1.6.0"
    const val material = "com.google.android.material:material:1.4.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
    const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val activityKtx = "androidx.activity:activity-ktx:1.3.1"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.6"
    const val splashScreen = "androidx.core:core-splashscreen:1.0.0-alpha02"
    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.1.5"
}

object Gradle {
    const val gradleTool = "com.android.tools.build:gradle:7.0.3"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
}

object Testing {
    const val junit = "junit:junit:4.13.2"
    const val junitAndroid = "androidx.test.ext:junit:1.1.3"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    const val truth = "com.google.truth:truth:1.1.3"
}

object Coil {
    private const val version = "1.4.0"
    const val coil = "io.coil-kt:coil:$version"
    const val gif = "io.coil-kt:coil-gif:$version"
    const val video = "io.coil-kt:coil-video:$version"
}

object Hilt {
    private const val version = "2.38.1"
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
}

object DataStore {
    private const val version = "1.0.0"
    const val dataStoreCore = "androidx.datastore:datastore-preferences-core:$version"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:$version"
}

object Coroutines {
    private const val version = "1.3.9"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
}

object Lifecycle {
    private const val version = "1.3.9"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
}

object Networking {
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val moshiRetrofitConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.2"
}

object Moshi {
    private const val version = "1.12.0"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    const val moshi = "com.squareup.moshi:moshi-kotlin:$version"

}

object StartUp {
    const val startUp = "androidx.startup:startup-runtime:1.1.0"
}

object LeakCanary {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.7"
}

object Browser {
    const val browser = "androidx.browser:browser:1.3.0"
}

object Timber {
    const val timber = "com.jakewharton.timber:timber:5.0.1"
}

object Navigation {
    private const val version = "2.3.5"
    const val navRuntime = "androidx.navigation:navigation-runtime-ktx:$version"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:$version"
    const val navUi = "androidx.navigation:navigation-ui-ktx:$version"
    const val navSafeArg = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
}

object Chucker{
    private const val version="3.5.2"
    const val chuckerLib="com.github.chuckerteam.chucker:library:3.5.2"
   const val chuckerLibNoOp="com.github.chuckerteam.chucker:library-no-op:3.5.2"
}

object KLint {
    const val pluginVersion = "10.1.0"
    const val version = "0.42.0"
    const val kLint = "org.jlleitschuh.gradle.ktlint"
}