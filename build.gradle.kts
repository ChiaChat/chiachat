plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false

    id(Plugin.Id.detekt) version Versions.detekt
    id(Plugin.Id.kover) version Versions.kover
    id(Plugin.Id.ktfmt) version Versions.ktfmtGradle
}



tasks {
    val detektAndCheck by
    registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        dependsOn(":check")
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

subprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

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
}
