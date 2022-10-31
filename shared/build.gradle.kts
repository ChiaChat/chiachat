plugins {
  kotlin("multiplatform")
  kotlin("native.cocoapods")
}

version = "1.0"

kotlin {
  jvm()
  js(IR) { browser { commonWebpackConfig { cssSupport.enabled = true } } }
  iosArm64()
  iosX64()
  iosSimulatorArm64()

  cocoapods {
    summary = "Some description for the shared Module"
    homepage = "Link to the shared Module homepage"
    ios.deploymentTarget = "14.0"
    podfile = project.file("../iosApp/Podfile")
    framework { baseName = "shared" }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(Deps.Log.kermit)
        api(Deps.Kotlinx.coroutines)
        api(Deps.Koin.core)
        api(Deps.Kor.korio)
      }
    }
    val commonTest by getting {
      dependencies {
        api(kotlin("test"))
        with(Deps.Test) { api(coroutines) }
      }
    }
    val jvmMain by getting { dependencies {} }
    val jvmTest by getting
    val jsMain by getting
    val jsTest by getting
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
