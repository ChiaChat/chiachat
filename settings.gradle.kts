pluginManagement {
  repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.hq.hydraulic.software")
  }
}

rootProject.name = "chiachat"

// include(":androidApp")

include(":iosApp")

include(":desktopApp")

include(":webApp")

include(":ui")

include(":shared")
