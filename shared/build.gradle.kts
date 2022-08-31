plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id(Plugin.Id.apollo)
    id("com.rickclephas.kmp.nativecoroutines") version Versions.nativeCoroutines
    id(Plugin.Id.mokoResources)
}

version = "1.0"

kotlin {
    jvm()
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the shared Module"
        homepage = "Link to the shared Module homepage"
        ios.deploymentTarget = "14.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            export(Deps.Moko.resourcesCommon)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Deps.Log.kermit)
                api(Deps.Apollo.runtime)
                api(Deps.Kotlinx.coroutines)
                api(Deps.Koin.core)
                api(Deps.Network.cognito)
                api(Deps.Moko.resourcesCommon)
            }
        }
        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
                with(Deps.Test) {
                    api(coroutines)
                    api(mokoResources)
                }
            }
        }
        val jvmMain by getting {
            dependencies {
                api(Deps.Moko.resourcesCompose)
            }
        }
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
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

apollo {
    packageName.set("com.dialexa.mnp.apollo")
    service("LocalMnpServer") {
        packageName.set("com.dialexa.mnp.apollo")
        introspection {
            endpointUrl.set("http://localhost:3000/graphql/")
            schemaFile.set(file("src/commonMain/graphql/com/dialexa/mnp/schema.graphqls"))
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.dialexa.mnp" // required
    multiplatformResourcesClassName = "MR" // optional, default MR
    iosBaseLocalizationRegion = "en" // optional, default "en"
    multiplatformResourcesSourceSet = "commonMain"  // optional, default "commonMain"
}