import java.io.FileInputStream
import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt.compiler) apply false
    alias(libs.plugins.vkid.manifest) apply true
    alias(libs.plugins.google.devtools.ksp)
}

vkidManifestPlaceholders {
    var clientId: String
    var clientSecret: String

    try {
        val secretsValuesFile = rootProject.file("secrets.properties")
        val secretsProperties = Properties()
        secretsProperties.load(FileInputStream(secretsValuesFile))

        clientId = secretsProperties.getProperty("clientId")
        clientSecret = secretsProperties.getProperty("clientSecret")
    } catch (e: Exception) {
        clientId = "nothing"
        clientSecret = "nothing"
    }
    init(
        clientId = clientId,
        clientSecret = clientSecret,
    )


    vkidRedirectHost = "vk.com" // vk.com.
    vkidRedirectScheme = "vk${clientId}" // Строго в формате vk{ID приложения}.
    vkidClientId = clientId
    vkidClientSecret = clientSecret
}