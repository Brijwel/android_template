// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Gradle.gradleTool)
        classpath(Gradle.kotlinGradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files

        classpath(Hilt.hiltGradlePlugin)
        classpath(Navigation.navSafeArg)
    }
}

plugins {
    id(KLint.kLint) version KLint.pluginVersion
}
subprojects {
    apply {
        plugin(KLint.kLint)
    }
}
ktlint {
    debug.set(false)
    version.set(KLint.version)
    verbose.set(true)
    android.set(false)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    enableExperimentalRules.set(true)
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
