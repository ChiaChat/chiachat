plugins {
  id("com.android.application")
  kotlin("android")
  id("org.jetbrains.compose") version Versions.composeMultiplatform
}

val resPath = "src/commonMain/resources"

android {
  compileSdk = 33
  defaultConfig {
    applicationId = "org.chiachat.app.android"
    minSdk = 26
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions { jvmTarget = "17" }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  sourceSets["main"].apply {
    assets.srcDirs(project(":ui").file(resPath), project(":shared").file(resPath))
  }
}

dependencies {
  implementation(project(":ui"))
  implementation("androidx.activity:activity-compose:1.6.1")
  implementation(Deps.Koin.compose)

  /*testImplementation(Deps.Test.junitApi)
  testRuntimeOnly(Deps.Test.junitEngine)
  androidTestImplementation("androidx.test:runner:1.4.0")
  androidTestImplementation(Deps.Test.junitApi)
  androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.3.0")
  androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.3.0")*/
}
