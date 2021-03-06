plugins {
    kotlin("jvm")
}

group = "com.wheejuni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation(project(":runtracker-domain"))
    implementation(kotlin("stdlib"))
}
