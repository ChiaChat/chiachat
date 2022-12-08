plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

kotlin {
    jvm()
    js(IR) { browser { commonWebpackConfig { cssSupport.enabled = true } } }
    android()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared"))
                api(compose.ui)
                api(compose.foundation)
                api(compose.runtime)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class) api(compose.material3)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                with(Deps.Test) {
                    implementation(koin)
                    implementation(coroutines)
                }
            }
        }
        val jvmMain by getting {
            dependsOn(commonMain)
        }
        val jvmTest by getting { dependencies { api(project(":shared")) } }
        val jsMain by getting { dependsOn(commonMain) }
        val jsTest by getting
        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.5.1")
                implementation("androidx.core:core-ktx:1.9.0")
                implementation("androidx.compose.ui:ui-graphics:1.4.0-alpha01")
                dependencies { api(compose.preview) }
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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

android {
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
            assets.srcDirs("src/commonMain/resources")
        }
    }
    namespace = "org.chiachat.app.ui"
}
