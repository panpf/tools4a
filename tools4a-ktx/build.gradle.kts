import com.novoda.gradle.release.PublishExtension
import java.util.*

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("jacoco")
}

android {
    compileSdkVersion(property("COMPILE_SDK_VERSION").toString().toInt())

    defaultConfig {
        minSdkVersion(property("MIN_SDK_VERSION").toString().toInt())
        targetSdkVersion(property("TARGET_SDK_VERSION").toString().toInt())
        versionCode = property("VERSION_CODE").toString().toInt()
        versionName = property("VERSION_NAME").toString()

        consumerProguardFiles("proguard-rules.pro")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${property("KOTLIN_VERSION")}")
    api(project(":tools4a-activity-ktx"))
    api(project(":tools4a-args-ktx"))
    api(project(":tools4a-clipboard-ktx"))
    api(project(":tools4a-content-ktx"))
    api(project(":tools4a-device-ktx"))
    api(project(":tools4a-dialog-ktx"))
    api(project(":tools4a-dimen-ktx"))
    api(project(":tools4a-display-ktx"))
    api(project(":tools4a-fileprovider-ktx"))
    api(project(":tools4a-fragment-ktx"))
    api(project(":tools4a-graphics-ktx"))
    api(project(":tools4a-inputmethod-ktx"))
    api(project(":tools4a-network-ktx"))
    api(project(":tools4a-packages-ktx"))
    api(project(":tools4a-permission-ktx"))
    api(project(":tools4a-prefsdelegate"))
    api(project(":tools4a-rom"))
    api(project(":tools4a-run-ktx"))
    api(project(":tools4a-service-ktx"))
    api(project(":tools4a-settings-ktx"))
    api(project(":tools4a-storage-ktx"))
    api(project(":tools4a-systemproperties"))
    api(project(":tools4a-toast-ktx"))
    api(project(":tools4a-utils-ktx"))
    api(project(":tools4a-view-ktx"))

    testImplementation("junit:junit:${property("JUNIT")}")
    androidTestImplementation("androidx.test:runner:${property("TEST_RUNNER")}")
    androidTestImplementation("androidx.test:rules:${property("TEST_RULES")}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${property("TEST_ESPRESSO")}")
}

Properties().apply { project.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) } }.takeIf { !it.isEmpty }?.let { localProperties ->
    apply { plugin("com.novoda.bintray-release") }

    configure<PublishExtension> {
        groupId = "com.github.panpf.tools4a"
        artifactId = "tools4a-ktx"
        publishVersion = property("VERSION_NAME").toString()
        desc = "Android, Tools, Ktx"
        website = "https://github.com/panpf/tools4a"
        userOrg = localProperties.getProperty("bintray.userOrg")
        bintrayUser = localProperties.getProperty("bintray.user")
        bintrayKey = localProperties.getProperty("bintray.apikey")
    }
}
