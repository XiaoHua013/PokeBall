plugins {
    `maven-publish`
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "com.entiv"
version = "1.0.4"

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
}

dependencies {
    implementation("com.entiv:insekicore:1.0.8")

    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))

    compileOnly("org.purpurmc.purpur", "purpur-api", "1.19-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.0")
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.10.0")
//    compileOnly("com.djrapitops:plan-api:5.2-R0.1")
//    compileOnly("com.zaxxer:HikariCP:5.0.1")
//    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
//    compileOnly("com.plotsquared:PlotSquared-Core:6.9.0")
    compileOnly(fileTree("libs"))
}

tasks {

    shadowJar {
        project.findProperty("outputPath")?.let {
            destinationDirectory.set(file(it.toString()))
        }

        archiveFileName.set("${project.name}-${project.version}.jar")
        relocate("com.entiv.core", "${project.group}.lib")

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
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

