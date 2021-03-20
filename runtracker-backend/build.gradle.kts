import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.3.31"

plugins {
    kotlin("plugin.jpa") version "1.3.31"
    kotlin("jvm") version "1.3.31"
    kotlin("plugin.spring") version "1.3.31"

    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("com.google.cloud.tools.jib") version "0.10.0"
    id("org.asciidoctor.convert") version "1.5.8"
}

group = "com.wheejuni"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "idea")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "jacoco")

    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        maven(url = "http://repo.spring.io/milestone")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:2.4.4")
        implementation("org.apache.httpcomponents:httpclient")
        implementation("mysql:mysql-connector-java")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("net.logstash.logback:logstash-logback-encoder:5.2")
        implementation("org.jetbrains.kotlin:kotlin-noarg:${kotlinVersion}")
        implementation("io.github.microutils:kotlin-logging:1.7.6")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.getsentry.raven:raven-logback:8.0.3")
        implementation("org.springframework.vault:spring-vault-core:2.1.3.RELEASE")
        implementation("org.springframework.kafka:spring-kafka")
        implementation("com.auth0:java-jwt:3.3.0")
        implementation("com.h2database:h2")
        implementation("io.r2dbc:r2dbc-h2:0.8.4.RELEASE")
        testImplementation("org.springframework.kafka:spring-kafka-test")
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(module = "junit")
        }
        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
        testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.3")
        testImplementation("io.kotlintest:kotlintest-extensions-spring:3.3.3")
        testImplementation("io.mockk:mockk:1.9.1")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}



repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}
