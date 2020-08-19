import com.novoda.gradle.release.PublishExtension
import java.util.*

plugins {
    id("com.android.library")
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
    api("androidx.annotation:annotation:${property("ANDROIDX_ANNOTATION")}")

    testImplementation("junit:junit:${property("JUNIT")}")
    androidTestImplementation("androidx.test:runner:${property("TEST_RUNNER")}")
    androidTestImplementation("androidx.test:rules:${property("TEST_RULES")}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${property("TEST_ESPRESSO")}")
    androidTestImplementation("com.github.panpf.tools4j:tools4j-premise:${property("TOOLS4J")}")
    androidTestImplementation("com.github.panpf.tools4j:tools4j-io:${property("TOOLS4J")}")
    androidTestImplementation("com.github.panpf.tools4j:tools4j-collections:${property("TOOLS4J")}")
    androidTestImplementation("com.github.panpf.tools4j:tools4j-math:${property("TOOLS4J")}")
    androidTestImplementation(project(":tools4a-storage"))
}

Properties().apply { project.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) } }.takeIf { !it.isEmpty }?.let { localProperties ->
    apply { plugin("com.novoda.bintray-release") }

    configure<PublishExtension> {
        groupId = "com.github.panpf.tools4a"
        artifactId = "tools4a-graphics"
        publishVersion = property("VERSION_NAME").toString()
        desc = "Android, Tools, Graphics"
        website = "https://github.com/panpf/tools4a"
        userOrg = localProperties.getProperty("bintray.userOrg")
        bintrayUser = localProperties.getProperty("bintray.user")
        bintrayKey = localProperties.getProperty("bintray.apikey")
    }
}