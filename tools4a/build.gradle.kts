plugins {
    id("com.android.library")
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
    api(project(":tools4a-activity"))
    api(project(":tools4a-args"))
    api(project(":tools4a-build"))
    api(project(":tools4a-clipboard"))
    api(project(":tools4a-context"))
    api(project(":tools4a-device"))
    api(project(":tools4a-dialog"))
    api(project(":tools4a-dimen"))
    api(project(":tools4a-display"))
    api(project(":tools4a-fileprovider"))
    api(project(":tools4a-fragment"))
    api(project(":tools4a-graphics"))
    api(project(":tools4a-inputmethod"))
    api(project(":tools4a-intent"))
    api(project(":tools4a-network"))
    api(project(":tools4a-packages"))
    api(project(":tools4a-permission"))
    api(project(":tools4a-rom"))
    api(project(":tools4a-root"))
    api(project(":tools4a-run"))
    api(project(":tools4a-service"))
    api(project(":tools4a-settings"))
    api(project(":tools4a-storage"))
    api(project(":tools4a-systemproperties"))
    api(project(":tools4a-toast"))
    api(project(":tools4a-utils"))
    api(project(":tools4a-view"))

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
