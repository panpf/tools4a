plugins {
    id("com.android.library")
    id("kotlin-android")
    id("jacoco")
}

android {
    compileSdk = property("COMPILE_SDK_VERSION").toString().toInt()

    defaultConfig {
        minSdk = property("MIN_SDK_VERSION").toString().toInt()
        targetSdk = property("TARGET_SDK_VERSION").toString().toInt()

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
    api(project(":tools4a-build"))
    api(project(":tools4a-clipboard-ktx"))
    api(project(":tools4a-context-ktx"))
    api(project(":tools4a-device-ktx"))
    api(project(":tools4a-dialog-ktx"))
    api(project(":tools4a-dimen-ktx"))
    api(project(":tools4a-display-ktx"))
    api(project(":tools4a-fileprovider-ktx"))
    api(project(":tools4a-fragment-ktx"))
    api(project(":tools4a-graphics-ktx"))
    api(project(":tools4a-inputmethod-ktx"))
    api(project(":tools4a-intent-ktx"))
    api(project(":tools4a-network-ktx"))
    api(project(":tools4a-packages-ktx"))
    api(project(":tools4a-permission-ktx"))
    api(project(":tools4a-prefsdelegate"))
    api(project(":tools4a-rom"))
    api(project(":tools4a-root"))
    api(project(":tools4a-run-ktx"))
    api(project(":tools4a-service-ktx"))
    api(project(":tools4a-settings-ktx"))
    api(project(":tools4a-storage-ktx"))
    api(project(":tools4a-systemproperties"))
    api(project(":tools4a-toast-ktx"))
    api(project(":tools4a-utils"))
    api(project(":tools4a-view-ktx"))

    testImplementation("junit:junit:${property("JUNIT")}")
    androidTestImplementation("androidx.test:runner:${property("ANDROIDX_TEST_RUNNER")}")
    androidTestImplementation("androidx.test:rules:${property("ANDROIDX_TEST_RULES")}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${property("ANDROIDX_TEST_ESPRESSO")}")
    androidTestImplementation("androidx.test.ext:junit:${property("ANDROIDX_TEST_EXT_JUNIT")}")
}

/**
 * publish config
 */
if (hasProperty("signing.keyId")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("signing.password")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("signing.secretKeyRingFile")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("mavenCentralUsername")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("mavenCentralPassword")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("GROUP")    // configured in the rootProject/gradle.properties file
    && hasProperty("POM_ARTIFACT_ID")    // configured in the project/gradle.properties file
) {
    apply { plugin("com.github.panpf.maven.publish") }

    configure<com.github.panpf.maven.publish.MavenPublishPluginExtension> {
        sonatypeHost = com.github.panpf.maven.publish.SonatypeHost.S01
        disableAndroidJavaDocsAddReferencesLinks = true
    }
}