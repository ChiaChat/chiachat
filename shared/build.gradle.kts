plugins {
  kotlin("multiplatform")
  id("app.cash.sqldelight") version "2.0.0-alpha04"
  //  id("com.android.library")
  id(Plugin.Id.kover)
  //  id("com.google.devtools.ksp") version "1.7.20-1.0.8"
}

version = "1.0"

kotlin {
  jvm()
  js(IR) { browser {} }
  // android()
  iosArm64()
  iosX64()
  iosSimulatorArm64()

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(Deps.Log.kermit)
        api(Deps.Kotlinx.coroutines)
        api(Deps.Koin.core)
        api(Deps.Kor.korio)
        api(Deps.Matrix.Client)
        with(Deps.Utility) {
          api(mpsettings)
          api(mpsettingsNoArgs)
        }
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        //        implementation("io.mockative:mockative:1.2.3")
        with(Deps.Test) {
          implementation(koin)
          implementation(coroutines)
        }
      }
    }
    val jvmMain by getting { dependencies { implementation(Deps.Sqldelight.sqliteJvmDriver) } }
    val jvmTest by getting {}
    val jsMain by getting { dependencies { implementation(Deps.Sqldelight.sqliteJsDriver) } }
    val jsTest by getting
    //    val androidMain by getting {
    //      dependencies { implementation(Deps.Sqldelight.sqliteAndroidDriver) }
    //    }
    //    val androidTest by getting { dependencies { implementation("junit:junit:4.13.2") } }
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)

      dependencies { implementation(Deps.Sqldelight.sqliteNativeDriver) }
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

/*dependencies {
  configurations
    .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
    .forEach {
      add(it.name, "io.mockative:mockative-processor:1.2.3")
    }
}*/

sqldelight {
  database("ChiaChatDb") { // This will be the name of the generated database class.
    packageName = "org.chiachat.app"
    dialect(Deps.Sqldelight.sqliteDialect)
    deriveSchemaFromMigrations = true
    verifyMigrations = true
  }
}

/*android {
   compileSdk = 33
   defaultConfig {
     minSdk = 26
     targetSdk = 33
     testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
   }
   compileOptions {
     sourceCompatibility = JavaVersion.VERSION_17
     targetCompatibility = JavaVersion.VERSION_17
   }

   sourceSets {
     named("main") {
       manifest.srcFile("src/androidMain/AndroidManifest.xml")
       res.srcDirs("src/androidMain/res")
     }
   }
   namespace = "org.chiachat.app.shared"
 }*/
