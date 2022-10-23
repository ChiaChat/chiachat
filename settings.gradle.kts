pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "chiachat"
include(":androidApp")
include(":iosApp")
include(":desktopApp")
include(":ui")
include(":shared")
