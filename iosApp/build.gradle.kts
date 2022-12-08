import org.jetbrains.compose.experimental.dsl.IOSDevices
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version Versions.composeMultiplatform
}

version = "1.0-SNAPSHOT"

kotlin {
  iosX64("uikitX64") {
    binaries {
      executable() {
        entryPoint = "main"
        freeCompilerArgs +=
            listOf(
                "-linker-option",
                "-framework",
                "-linker-option",
                "Metal",
                "-linker-option",
                "-framework",
                "-linker-option",
                "CoreText",
                "-linker-option",
                "-framework",
                "-linker-option",
                "CoreGraphics")
      }
    }
  }
  iosArm64("uikitArm64") {
    binaries {
      executable() {
        entryPoint = "main"
        freeCompilerArgs +=
            listOf(
                "-linker-option",
                "-framework",
                "-linker-option",
                "Metal",
                "-linker-option",
                "-framework",
                "-linker-option",
                "CoreText",
                "-linker-option",
                "-framework",
                "-linker-option",
                "CoreGraphics")
        // TODO: the current compose binary surprises LLVM, so disable checks for now.
        freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
      }
    }
  }

  sourceSets {
    val commonMain by getting { dependencies { implementation(project(":shared")) } }

    val nativeMain by creating { dependsOn(commonMain) }
    val uikitMain by creating { dependsOn(nativeMain) }
    val uikitX64Main by getting { dependsOn(uikitMain) }
    val uikitArm64Main by getting { dependsOn(uikitMain) }
  }
}

compose.experimental {
  web.application {}
  uikit.application {
    bundleIdPrefix = "org.chiachat.app"
    projectName = "ChiaChat"
    deployConfigurations {
      simulator("IPhone13Pro") {
        // Usage: ./gradlew iosDeployIPhone8Debug
        device = IOSDevices.IPHONE_13_PRO
      }
      simulator("IPad") {
        // Usage: ./gradlew iosDeployIPadDebug
        device = IOSDevices.IPAD_MINI_6th_Gen
      }
      connectedDevice("Device") {
        // Usage: ./gradlew iosDeployDeviceRelease
        this.teamId = "***"
      }
    }
  }
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "17" }

kotlin {
  targets.withType<KotlinNativeTarget> {
    binaries.all {
      // TODO: the current compose binary surprises LLVM, so disable checks for now.
      freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
    }
  }
}
