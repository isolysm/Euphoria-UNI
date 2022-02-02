/*
All Rights Reserved

Copyright (c) 2022 Myosyn LLC

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecraftforge.gradle.user.IReobfuscator
import net.minecraftforge.gradle.user.ReobfMappingType.SEARGE
import net.minecraftforge.gradle.user.TaskSingleReobf
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("net.minecraftforge.gradle.forge") version "6f5327"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.spongepowered.mixin") version "d5f9873d60"
    java
}

version = "0.0.1"
group = "xyz.myosyn"

minecraft {
    version = "1.8.9-11.15.1.2318-1.8.9"
    runDir = "run"
    mappings = "stable_22"
    makeObfSourceJar = false
    isGitVersion = false
    clientJvmArgs + arrayOf(
        "-Delementa.dev=true",
        "-Delementa.debug=true"
    )
    clientRunArgs + arrayOf(
        "--tweakClass gg.essential.loader.stage0.EssentialSetupTweaker",
        "--mixin mixins.simpletogglesprint.json"
    )
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.spongepowered.org/repository/maven-public/")
    maven("https://repo.sk1er.club/repository/maven-public/")
    maven("https://repo.sk1er.club/repository/maven-releases/")
    maven("https://jitpack.io")
}

val shadowMe: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    annotationProcessor("org.spongepowered:mixin:0.7.11-SNAPSHOT")
    implementation("org.spongepowered:mixin:0.7.11-SNAPSHOT")

    shadowMe("gg.essential:loader-launchwrapper:1.1.3")
    implementation("gg.essential:essential-1.8.9-forge:1709")
}

mixin {
    disableRefMapWarning = true
    defaultObfuscationEnv = searge
    add(sourceSets.main.get(), "mixins.simpletogglesprint.refmap.json")
}

sourceSets {
    main {
        ext["refmap"] = "mixins.simpletogglesprint.refmap.json"
        output.setResourcesDir(file("${buildDir}/classes/kotlin/main"))
    }
}

configure<NamedDomainObjectContainer<IReobfuscator>> {
    clear()
    create("shadowJar") {
        mappingType = SEARGE
        classpath = sourceSets.main.get().compileClasspath
    }
}

tasks {
    processResources {
        inputs.property("version", project.version)
        inputs.property("mcversion", project.minecraft.version)

        filesMatching("mcmod.info") {
            expand(mapOf("version" to project.version, "mcversion" to project.minecraft.version))
        }
    }
    named<Jar>("jar") {
        archiveBaseName.set("SimpleToggleSprint")
        manifest {
            attributes(
                mapOf(
                    "FMLCorePluginContainsFMLMod" to true,
                    "ForceLoadAsMod" to true,
                    "MixinConfigs" to "mixins.simpletogglesprint.json",
                    "ModSide" to "CLIENT",
                    "TweakClass" to "gg.essential.loader.stage0.EssentialSetupTweaker",
                    "TweakOrder" to "0"
                )
            )
        }
        enabled = false
    }
    named<ShadowJar>("shadowJar") {
        archiveFileName.set(jar.get().archiveFileName)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        configurations = listOf(shadowMe)

        exclude(
            "**/LICENSE.md",
            "**/LICENSE.txt",
            "**/LICENSE",
            "**/NOTICE",
            "**/NOTICE.txt",
            "pack.mcmeta",
            "dummyThing",
            "**/module-info.class",
            "META-INF/proguard/**",
            "META-INF/maven/**",
            "META-INF/versions/**",
            "META-INF/com.android.tools/**",
            "fabric.mod.json"
        )
        mergeServiceFiles()
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
        }
        kotlinDaemonJvmArguments.set(listOf("-Xmx2G", "-Dkotlin.enableCacheBuilding=true", "-Dkotlin.useParallelTasks=true", "-Dkotlin.enableFastIncremental=true"))
    }
    named<TaskSingleReobf>("reobfJar") {
        enabled = false
    }
    named<TaskSingleReobf>("reobfShadowJar") {
        mustRunAfter(shadowJar)
    }
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8