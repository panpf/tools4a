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

/*
 * publish to bintray
 */
`java.util`.Properties().apply {
    rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
    project.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}.takeIf {
    it.getProperty("bintray.user") != null && it.getProperty("bintray.userOrg") != null && it.getProperty("bintray.apiKey") != null
}?.let { localProperties ->
    apply { plugin("com.github.panpf.bintray-publish") }
    configure<com.github.panpf.bintray.publish.PublishExtension> {
        groupId = "com.github.panpf.tools4a"
        artifactId = "tools4a"
        publishVersion = property("VERSION_NAME").toString()
        desc = "Android, Tools"
        website = "https://github.com/panpf/tools4a"
        userOrg = localProperties.getProperty("bintray.userOrg")
        bintrayUser = localProperties.getProperty("bintray.user")
        bintrayKey = localProperties.getProperty("bintray.apiKey")
    }
}
