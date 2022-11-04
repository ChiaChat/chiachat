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
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

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
}
