plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version Versions.composeMultiplatform
  id("com.android.library")
}

version = "1.0"

kotlin {
  jvm()
  js(IR) { browser { commonWebpackConfig { cssSupport.enabled = true } } }
  android()
  iosArm64()
  iosX64()
  iosSimulatorArm64()

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(project(":shared"))
        api(compose.ui)
        api(compose.foundation)
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class) api(compose.material3)
        api(compose.runtime)
      }
    }
    val commonTest by getting { dependencies { implementation(kotlin("test")) } }
    val jvmMain by getting {
      dependsOn(commonMain)
      dependencies { implementation(compose.preview) }
    }
    val jvmTest by getting
    val jsMain by getting { dependsOn(commonMain) }
    val jsTest by getting
    val androidMain by getting {
      dependsOn(commonMain)
      dependencies {
        api("androidx.appcompat:appcompat:1.5.1")
        api("androidx.core:core-ktx:1.9.0")
        api("androidx.compose.ui:ui-graphics:1.4.0-alpha01")
      }
    }
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
    }
    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}

android {
  compileSdk = 33

  defaultConfig {
    minSdk = 26
    targetSdk = 33
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  sourceSets {
    named("main") {
      manifest.srcFile("src/androidMain/AndroidManifest.xml")
      res.srcDirs("src/androidMain/res")
      assets.srcDirs("src/commonMain/resources")
    }
  }
}
