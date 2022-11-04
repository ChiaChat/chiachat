object Versions {
  const val kotlin = "1.7.10"
  const val detekt = "1.22.0-RC2"
  const val ktlint = "10.2.1"
  const val coroutines = "1.6.4"
  const val ktor = "2.0.1"
  const val mockito = "3.12.4"
  const val mockito_kotlin = "4.0.0"
  const val sqlDelight = "2.0.0-alpha04"
  const val postgres = "42.3.4"
  const val koin = "3.2.0-beta-1"
  const val junit = "5.8.2"
  const val proguard = "7.2.2"
  const val junitAndroid = "1.8.2.0"
  const val kermit = "1.1.2"
  const val kover = "0.5.1"
  const val mockmp = "1.8.0"
  const val compose = "1.3.0"
  const val composeCompiler = "1.3.0"
  const val activityCompose = "1.4.0-beta01"
  const val navCompose = "2.4.2"
  const val accompanist = "0.24.11-rc"
  const val composeMultiplatform = "1.2.0"
  const val nativeCoroutines = "0.12.5"
  const val cognitoIdp = "2.5.0"
  const val kor = "3.3.0"
  const val okio = "3.2.0"
  const val ktfmtGradle = "0.11.0"
  const val conveyor = "1.2"
  const val kotlinxSerialization = "1.4.1"
}

object Plugin {
  object Id {
    val detekt = "io.gitlab.arturbosch.detekt"
    val ktlint = "org.jlleitschuh.gradle.ktlint"
    val sqlDelight = "app.cash.sqldelight"
    val junit5 = "de.mannodermaus.android-junit5"
    val kover = "org.jetbrains.kotlinx.kover"
    val mockmp = "org.kodein.mock.mockmp"
    val compose = "org.jetbrains.compose"
    val mokoResources = "dev.icerock.mobile.multiplatform-resources"
    val ktfmt = "com.ncorti.ktfmt.gradle"
    val conveyor = "dev.hydraulic.conveyor"
  }

  val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
  val kotlinxSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
  val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
  val sqlDelight = "app.cash.sqldelight:gradle-plugin:${Versions.sqlDelight}"
  val proguard = "com.guardsquare:proguard-gradle:${Versions.proguard}"
  val junitAndroid = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.junitAndroid}"
  val kover = "org.jetbrains.kotlinx:kover:${Versions.kover}"
}

object Deps {
  object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
  }

  object Androidx {
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
  }

  object Compose {
    const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material3:material:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"

    const val coilCompose = "io.coil-kt:coil-compose:2.0.0"
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
  }

  object Kor {
    const val korio = "com.soywiz.korlibs.korio:korio:${Versions.kor}"
  }

  const val okio = "com.squareup.okio:okio:${Versions.okio}"

  object Kotlinx {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val protobuf =
        "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:${Versions.kotlinxSerialization}"
    const val json =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
  }

  object Ktor {
    const val network = "io.ktor:ktor-network:${Versions.ktor}"
    const val tls = "io.ktor:ktor-network-tls:${Versions.ktor}"
    const val tls_certs = "io.ktor:ktor-network-tls-certificates:${Versions.ktor}"
    const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"

    object Server {
      const val core = "io.ktor:ktor-server-core:${Versions.ktor}"
      const val auth = "io.ktor:ktor-server-auth:${Versions.ktor}"
      const val content_negotiation = "io.ktor:ktor-server-content-negotiation:${Versions.ktor}"
      const val netty = "io.ktor:ktor-server-netty:${Versions.ktor}"
    }

    object Client {
      const val auth = "io.ktor:ktor-client-auth:${Versions.ktor}"
      const val cio = "io.ktor:ktor-client-cio:${Versions.ktor}"
      const val content_negotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    }
  }

  object Log {
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
  }

  object Test {
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito_kotlin}"
    const val junitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
  }

  object Sqldelight {
    const val postgresDialect = "app.cash.sqldelight:postgresql-dialect:${Versions.sqlDelight}"
    const val jdbcDriver = "app.cash.sqldelight:jdbc-driver:${Versions.sqlDelight}"
    const val postgresDriver = "org.postgresql:postgresql:${Versions.postgres}"
    const val sqliteDialect = "app.cash.sqldelight:sqlite-3-38-dialect:${Versions.sqlDelight}"
    const val sqliteJsDriver = "app.cash.sqldelight:sqljs-driver:${Versions.sqlDelight}"
    const val sqliteJvmDriver = "app.cash.sqldelight:sqlite-driver:${Versions.sqlDelight}"
    const val sqliteNativeDriver = "app.cash.sqldelight:native-driver:${Versions.sqlDelight}"
    const val sqliteAndroidDriver = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
  }

  object Network {
    const val cognito = "com.liftric:cognito-idp:${Versions.cognitoIdp}"
  }
}
