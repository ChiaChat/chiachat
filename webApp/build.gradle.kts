import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version Versions.composeMultiplatform
}

val resPath = "src/commonMain/resources"

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }

  sourceSets {
    val jsMain by getting {
      dependencies {
        implementation(project(":ui"))
        implementation(compose.web.core)
      }
      this.resources.setSrcDirs(
          listOf(
              project(":shared").file(resPath),
              project(":ui").file(resPath),
              project.file("src/jsMain/resources")))
    }
    val jsTest by getting
  }
}

compose.experimental { web.application {} }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }

// a temporary workaround for a bug in jsRun invocation - see
// https://youtrack.jetbrains.com/issue/KT-48273
afterEvaluate {
  rootProject.extensions.configure<
      org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackDevServer.version = "4.0.0"
    versions.webpackCli.version = "4.10.0"
    nodeVersion = "16.0.0"
  }
}

// TODO: remove when https://youtrack.jetbrains.com/issue/KT-50778 fixed
project.tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile::class.java).configureEach {
  kotlinOptions.freeCompilerArgs += listOf("-Xir-dce-runtime-diagnostic=log")
}
