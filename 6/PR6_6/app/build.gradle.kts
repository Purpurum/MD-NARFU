plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}


@Suppress("UnstableApiUsage")
android {
    defaultConfig {
        applicationId = "ru.myitschool.lab23"
        versionCode = 1
        versionName = "0.0.1"

        targetSdk = 33
        minSdk = 28
        compileSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        warningsAsErrors = true
        ignoreWarnings = false
        abortOnError = true
        checkAllWarnings = true
        lintConfig = file("lint.xml")
        lint {
            disable.addAll(
                listOf(
                    "InvalidPackage",
                    "UnusedIds",
                    "GradleDependency",
                    "UnusedResources",
                    "SyntheticAccessor"
                )
            )
        }
    }

    applicationVariants.all {
        val lintTask = tasks["lint${name.capitalize()}"]
        assembleProvider.get().dependsOn.add(lintTask)
    }

    buildFeatures {
        viewBinding = true
    }
    namespace = "ru.myitschool.lab23"
}

dependencies {

    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.media:media:1.6.0")
    implementation("com.google.android.gms:play-services-wearable:17.1.0")
    implementation("androidx.percentlayout:percentlayout:1.0.0")
    implementation("androidx.wear:wear:1.2.0")

    androidTestImplementation(libs.kakao)
    androidTestImplementation(libs.androidx.test.uiautomator)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(kotlin("test"))
    testImplementation(libs.androidx.test.ext)

}