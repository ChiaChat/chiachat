import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version Versions.composeMultiplatform
  //  id(Plugin.Id.conveyor) version Versions.conveyor
}

repositories {
  mavenCentral()
  google()
  maven { url = uri("https://jitpack.io") }
  maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

kotlin {
  jvm {
    compilations.all { kotlinOptions.jvmTarget = "17" }
    withJava()
  }

  sourceSets {
    val jvmMain by getting {
      dependencies {
        implementation(project(":ui"))
        implementation(compose.desktop.currentOs)
      }
    }
    val jvmTest by getting
  }
}

/*dependencies {
  // Use the configurations created by the Conveyor plugin to tell Gradle/Conveyor where to find the
  // artifacts for each platform.
  linuxAmd64(compose.desktop.linux_x64)
  macAmd64(compose.desktop.macos_x64)
  macAarch64(compose.desktop.macos_arm64)
  windowsAmd64(compose.desktop.windows_x64)
}*/

val ico = File("../ui/src/commonMain/resources/icons/chiachat-trans-256x256.ico")
val icns = File("../ui/src/commonMain/resources/icons/chiachat-trans-256x256.icns")
val png = File("../ui/src/commonMain/resources/icons/chiachat-trans-256x256.png")

compose.desktop {
  application {
    mainClass = "org.chiachat.app.desktop.MainKt"

    buildTypes.release.proguard {
      this.isEnabled.set(true)
      configurationFiles.from(project.file("compose-desktop.pro"))
    }

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "ChiaChat"
      packageVersion = "1.0.0"

      modules("java.sql")

      macOS { iconFile.set(icns) }
      windows {
        iconFile.set(ico)
        console = true
      }
      linux {
        iconFile.set(png)
        packageName = "chia-chat"
        debMaintainer = "andrea@chiachat.org"
      }
    }
  }
}

configurations.all {
  attributes {
    // https://github.com/JetBrains/compose-jb/issues/1404#issuecomment-1146894731
    attribute(Attribute.of("ui", String::class.java), "awt")
  }
}

/*dependencies {
   // Force override the Kotlin stdlib version used by Compose to 1.7 in the machine specific
   // configurations, as otherwise we can end up
   // with a mix of 1.6 and 1.7 on our classpath. This is the same logic as is applied to the regular
   // Compose configurations normally.
   val v = "1.7.10"
   for (m in setOf("linuxAmd64", "macAmd64", "macAarch64", "windowsAmd64")) {
     m("org.jetbrains.kotlin:kotlin-stdlib:$v")
     m("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$v")
     m("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$v")
   }
 }*/
