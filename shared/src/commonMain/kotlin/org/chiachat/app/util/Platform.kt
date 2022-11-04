package org.chiachat.app.util

enum class Platform {
  IOS,
  ANDROID,
  JVM,
  JS
}

expect fun getPlatform(): Platform
