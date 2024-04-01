plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("plugin.serialization").version("1.9.23")
    id("com.squareup.sqldelight").version("1.5.5")
//    id("com.rickclephas.kmp.nativecoroutines") version "0.11.1"
}

kotlin {
    task("testClasses")
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val coroutinesVersion = "1.7.3"
        val ktorVersion = "2.3.7"
        val sqlDelightVersion = "1.5.5"
        val koinVersion = "3.5.3"
        val dateTimeVersion = "0.4.1"

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            val lifecycleVersion = "2.3.1"
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
            implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
            implementation("io.ktor:ktor-client-android:$ktorVersion")
            implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            api("io.insert-koin:koin-android:$koinVersion")

            val navigation = "androidx.navigation:navigation-compose:2.5.2"
            implementation(navigation)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")


            implementation("io.insert-koin:koin-core:$koinVersion")
//            api("io.insert-koin:koin-core:$koinVersion")
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")

            // Serialization
//            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

            // Sql Delight
            implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")

            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha03")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha03")
        }
        iosMain.dependencies {

        }
    }
}

android {
    namespace = "com.lindenlabs.photofeed"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.lindenlabs.photofeed"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
dependencies {
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.runtime.ktx)
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.lindenlabs.photofeed.db"
        sourceFolders = listOf("sqldelight")
    }
}