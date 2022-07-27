plugins {
    `maven-publish`
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "com.entiv"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "Paper"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }

    maven {
        name = "PlaceholderAPI"
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    maven {
        name = "jitpack"
        url = uri("https://jitpack.io")
    }

    maven {
        name = "WorldEdit"
        url = uri("https://maven.enginehub.org/repo/")
    }

    maven {
        name = "purpur"
        url = uri("https://repo.purpurmc.org/snapshots")
    }

    maven {
        name = "CodeMC"
        url = uri("https://repo.codemc.org/repository/maven-public/")
    }

    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots1"
    }
}

dependencies {
    implementation("com.entiv:insekicore:1.0.8")

    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))

    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.0")
    implementation("net.kyori:adventure-text-minimessage:4.11.0")
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.10.0")
    compileOnly(fileTree("libs"))
}

tasks {

    shadowJar {
        project.findProperty("outputPath")?.let {
            destinationDirectory.set(file(it.toString()))
        }

        archiveFileName.set("${project.name}-${project.version}.jar")
        relocate("com.entiv.core", "${project.group}.lib.core")
        relocate("net.kyori","${project.group}.lib")

        println("导出路径: ${destinationDirectory.get()}")
        println("")
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to project.name.toLowerCase(),
                "version" to project.version
            )
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(16))
}

