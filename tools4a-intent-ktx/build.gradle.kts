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
    api(project(":tools4a-intent"))

    testImplementation("junit:junit:${property("JUNIT")}")
    androidTestImplementation("androidx.test:runner:${property("ANDROIDX_TEST_RUNNER")}")
    androidTestImplementation("androidx.test:rules:${property("ANDROIDX_TEST_RULES")}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${property("ANDROIDX_TEST_ESPRESSO")}")
    androidTestImplementation("androidx.test.ext:junit:${property("ANDROIDX_TEST_EXT_JUNIT")}")
    androidTestImplementation("com.github.panpf.tools4j:tools4j-io-ktx:${property("TOOLS4J")}")
    androidTestImplementation(project(":tools4a-storage-ktx"))
    androidTestImplementation(project(":tools4a-fileprovider-ktx"))
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
        artifactId = "tools4a-intent-ktx"
        publishVersion = property("VERSION_NAME").toString()
        desc = "Android, Tools, Intent, Ktx"
        website = "https://github.com/panpf/tools4a"
        userOrg = localProperties.getProperty("bintray.userOrg")
        bintrayUser = localProperties.getProperty("bintray.user")
        bintrayKey = localProperties.getProperty("bintray.apiKey")
    }
    tasks.withType<GenerateModuleMetadata> {
        enabled = false
    }
}
