import java.io.FileInputStream
import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}

vkidManifestPlaceholders {
    val secretsValuesFile = rootProject.file("secrets.properties")
    val secretsProperties = Properties()
    secretsProperties.load(FileInputStream(secretsValuesFile))

    val clientId = secretsProperties.getProperty("clientId")
    val clientSecret = secretsProperties.getProperty("clientSecret")

    init(
        clientId = clientId,
        clientSecret = clientSecret,
    )


    vkidRedirectHost = "vk.com" // vk.com.
    vkidRedirectScheme = "vk${clientId}" // Строго в формате vk{ID приложения}.
    vkidClientId = clientId
    vkidClientSecret = clientSecret
}