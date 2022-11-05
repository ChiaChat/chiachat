buildscript {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    classpath("com.android.tools.build:gradle:7.2.2")
    classpath(Plugin.kotlin)
    classpath(Plugin.kotlinxSerialization)
    classpath(Plugin.proguard)
    classpath(Plugin.junitAndroid)
  }
}

plugins {
  id(Plugin.Id.detekt) version Versions.detekt
  id(Plugin.Id.kover) version Versions.kover
  id(Plugin.Id.ktfmt) version Versions.ktfmtGradle
}

group = "org.chiachat"

version = "1.0.0"

tasks {
  val checkAndDetekt by
      registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        dependsOn("check")
        description = "Runs detekt static code analysis on all modules"
        buildUponDefaultConfig = true
        allRules = true
        parallel = true
        ignoreFailures = false
        setSource(files(projectDir))
        include("**/*.kt")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom("$rootDir/config/detekt/detekt.yml")
        baseline.set(file("$rootDir/config/detekt/baseline.xml"))
        reports {
          html.required.set(true)
          xml.required.set(true)
          sarif.required.set(true)
        }
      }
  val detektProjectBaseline by
      registering(io.gitlab.arturbosch.detekt.DetektCreateBaselineTask::class) {
        description = "Overrides current baseline."
        buildUponDefaultConfig.set(true)
        ignoreFailures.set(true)
        parallel.set(true)
        setSource(files(rootDir))
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        baseline.set(file("$rootDir/config/detekt/baseline.xml"))
        include("**/*.kt")
        exclude("**/resources/**")
        exclude("**/build/**")
      }
}

val detektReportMergeSarif by
    tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
      output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.sarif"))
    }

allprojects {
  repositories {
    mavenCentral()
    mavenLocal()
    google()
    maven { url = uri("https://jitpack.io") }
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

subprojects {
  apply(plugin = Plugin.Id.kover)
  apply(plugin = Plugin.Id.detekt)

  detekt {
    source =
        objects
            .fileCollection()
            .from(
                io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_JAVA,
                io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_JAVA,
                io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
                io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN,
            )
    buildUponDefaultConfig = true
    baseline = file("$rootDir/config/detekt/baseline.xml")
  }

  tasks.withType<io.gitlab.arturbosch.detekt.Detekt> detekt@{
    jvmTarget = "17"
    reports {
      xml.required.set(true)
      html.required.set(true)
      txt.required.set(true)
      sarif.required.set(true)
      md.required.set(true)
    }
    basePath = rootProject.projectDir.absolutePath
    finalizedBy(detektReportMergeSarif)
    detektReportMergeSarif.configure { input.from(this@detekt.sarifReportFile) }
  }
  tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "17"
  }
  tasks {
    whenTaskAdded {
      if (name == "testDebugUnitTest") {
        this.enabled = false
      }
    }
  }
}

val rootPackage = "org.chiachat.app"
val basePackage = listOf("*", "").map { "$rootPackage.it" }
val excludedPackages =
    listOf("type", "selections", "adapter", "apollo", "test", "android.test", "compose", "").map {
      "$rootPackage.$it.*"
    } + "*.BuildConfig" + "*.MainActivity" + "*.**Test"

koverMerged {
  enable() // create Kover merged report tasks from this project and subprojects with enabled Kover
  // plugin

  filters {
    classes {
      includes += basePackage
      excludes += excludedPackages
    }
  }

  xmlReport {
    enable()
    onCheck.set(true)
    reportFile.set(layout.buildDirectory.file("reports/kover/xml/report.xml"))
  }

  htmlReport {
    enable()
    onCheck.set(true)
    reportDir.set(layout.buildDirectory.dir("reports/kover/html"))
  }

  verify {

    // set to true to run koverMergedVerify task during the execution of the check task
    // (if it exists) of the current project
    onCheck.set(true)
    rule { // add verification rule
      // set to false to disable rule checking
      isEnabled = true
      // custom name for the rule
      name = null
      // specify by which entity the code for separate coverage evaluation will be
      target = kotlinx.kover.api.VerificationTarget.ALL
      // grouped

      bound { // add rule bound
        minValue = 75
        maxValue = 100
        // change coverage metric to evaluate (LINE, INSTRUCTION, BRANCH)
        counter = kotlinx.kover.api.CounterType.LINE
        // change counter value (COVERED_COUNT, MISSED_COUNT,
        // COVERED_PERCENTAGE, MISSED_PERCENTAGE)
        valueType = kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE
      }
    }
  }
}
