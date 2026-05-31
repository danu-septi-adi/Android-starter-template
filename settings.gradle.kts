import groovy.json.JsonSlurper

// ══════════════════════════════════════════════════════
// Baca project name dari project-config.json
// ══════════════════════════════════════════════════════
val configFile = file("project-config.json")
if (configFile.exists()) {
    val config = JsonSlurper().parse(configFile) as Map<*, *>
    val projectCfg = config["project"] as Map<*, *>
    rootProject.name = projectCfg["name"] as String
} else {
    rootProject.name = "TemplateApp"
}

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
